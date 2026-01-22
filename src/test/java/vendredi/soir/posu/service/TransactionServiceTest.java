package vendredi.soir.posu.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import vendredi.soir.posu.endpoint.rest.mapper.TransactionMapper;
import vendredi.soir.posu.endpoint.rest.model.TransactionMinimalInfo;
import vendredi.soir.posu.model.Transaction;
import vendredi.soir.posu.model.User;
import vendredi.soir.posu.model.Wallet;
import vendredi.soir.posu.repository.TransactionRepository;
import vendredi.soir.posu.repository.WalletRepository;

class TransactionServiceTest {

  @Mock private TransactionRepository transactionRepository;
  @Mock private WalletRepository walletRepository;
  @Mock private TransactionMapper transactionMapper;
  @InjectMocks private TransactionService transactionService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  private void mockUser(User user) {
    SecurityContext securityContext = mock(SecurityContext.class);
    when(securityContext.getAuthentication())
        .thenReturn(new UsernamePasswordAuthenticationToken(user, null));
    SecurityContextHolder.setContext(securityContext);
  }

  @Test
  void recordTransactions_should_succeed_if_wallet_belongs_to_user() {
    User user = User.builder().id("user1").username("user1").build();
    mockUser(user);

    TransactionMinimalInfo info = TransactionMinimalInfo.builder().walletReference("W1").build();
    Wallet wallet = Wallet.builder().reference("W1").users(List.of(user)).build();
    Transaction transaction = new Transaction();

    when(walletRepository.findByReferenceAndUsersContaining("W1", user)).thenReturn(Optional.of(wallet));
    when(transactionMapper.toDomain(info, user)).thenReturn(transaction);
    when(transactionRepository.saveAll(any())).thenAnswer(i -> i.getArguments()[0]);

    List<Transaction> result = transactionService.recordTransactions(List.of(info));

    assertEquals(1, result.size());
    verify(transactionMapper).toDomain(info, user);
  }

  @Test
  void recordTransactions_should_throw_exception_if_wallet_not_found_for_user() {
    User user = User.builder().id("user1").username("user1").build();
    mockUser(user);

    TransactionMinimalInfo info = TransactionMinimalInfo.builder().walletReference("W1").build();

    when(walletRepository.findByReferenceAndUsersContaining("W1", user)).thenReturn(Optional.empty());

    assertThrows(IllegalArgumentException.class, () -> transactionService.recordTransactions(List.of(info)));
  }
}
