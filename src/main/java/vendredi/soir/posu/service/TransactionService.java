package vendredi.soir.posu.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vendredi.soir.posu.endpoint.rest.mapper.TransactionMapper;
import vendredi.soir.posu.endpoint.rest.model.TransactionMinimalInfo;
import vendredi.soir.posu.model.Transaction;
import vendredi.soir.posu.model.User;
import vendredi.soir.posu.model.Wallet;
import vendredi.soir.posu.repository.TransactionRepository;
import vendredi.soir.posu.repository.WalletRepository;

@Service
@AllArgsConstructor
public class TransactionService {
  private final TransactionRepository transactionRepository;
  private final WalletRepository walletRepository;
  private final TransactionMapper transactionMapper;

  public List<Transaction> recordTransactions(List<TransactionMinimalInfo> transactions) {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    List<Transaction> toSave =
        transactions.stream()
            .map(
                transaction -> {
                  Wallet wallet =
                      walletRepository
                          .findByReference(transaction.getWalletReference())
                          .orElseThrow(
                              () ->
                                  new IllegalArgumentException(
                                      "Wallet not found: " + transaction.getWalletReference()));
                  if (!wallet.getUsers().contains(currentUser)) {
                    throw new IllegalArgumentException(
                        "Wallet " + transaction.getWalletReference() + " does not belong to user");
                  }
                  return transactionMapper.toDomain(transaction);
                })
            .collect(Collectors.toList());
    return transactionRepository.saveAll(toSave);
  }

  public List<Transaction> getAllTransactions() {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    List<String> userWalletReferences =
        walletRepository.findByUsersContaining(currentUser).stream()
            .map(Wallet::getReference)
            .collect(Collectors.toList());
    return transactionRepository.findByWalletReferenceIn(userWalletReferences);
  }
}
