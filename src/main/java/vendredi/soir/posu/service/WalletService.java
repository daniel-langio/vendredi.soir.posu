package vendredi.soir.posu.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vendredi.soir.posu.endpoint.rest.mapper.WalletMapper;
import vendredi.soir.posu.endpoint.rest.model.WalletMinimalInfo;
import vendredi.soir.posu.model.Wallet;
import vendredi.soir.posu.repository.WalletRepository;

@Service
@AllArgsConstructor
public class WalletService {
  private final WalletRepository walletRepository;
  private final WalletMapper walletMapper;

  public List<Wallet> createWallets(List<WalletMinimalInfo> wallets) {
    List<Wallet> toSave =
        wallets.stream()
            .map(walletMapper::toDomain)
            .peek(
                wallet -> {
                  if (walletRepository.existsByReference(wallet.getReference())) {
                    throw new IllegalArgumentException(
                        "Wallet with reference " + wallet.getReference() + " already exists");
                  }
                })
            .collect(Collectors.toList());
    return walletRepository.saveAll(toSave);
  }

  public List<Wallet> getAllWallets() {
    return walletRepository.findAll();
  }
}
