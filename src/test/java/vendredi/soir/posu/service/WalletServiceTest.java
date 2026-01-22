package vendredi.soir.posu.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import vendredi.soir.posu.endpoint.rest.mapper.WalletMapper;
import vendredi.soir.posu.endpoint.rest.model.WalletMinimalInfo;
import vendredi.soir.posu.model.User;
import vendredi.soir.posu.model.Wallet;
import vendredi.soir.posu.repository.WalletRepository;

class WalletServiceTest {

  @Mock private WalletRepository walletRepository;
  @Mock private WalletMapper walletMapper;
  @InjectMocks private WalletService walletService;

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
  void createWallets_should_succeed_if_reference_unique_for_user() {
    User user = User.builder().id("user1").username("user1").build();
    mockUser(user);

    WalletMinimalInfo info = WalletMinimalInfo.builder().name("W1").reference("REF1").build();
    Wallet wallet = Wallet.builder().name("W1").reference("REF1").build();

    when(walletMapper.toDomain(info)).thenReturn(wallet);
    when(walletRepository.existsByReferenceAndUsersContaining("REF1", user)).thenReturn(false);
    when(walletRepository.saveAll(any())).thenAnswer(i -> i.getArguments()[0]);

    List<Wallet> result = walletService.createWallets(List.of(info));

    assertEquals(1, result.size());
    assertEquals("REF1", result.get(0).getReference());
    assertEquals(List.of(user), result.get(0).getUsers());
  }

  @Test
  void createWallets_should_throw_exception_if_reference_already_exists_for_user() {
    User user = User.builder().id("user1").username("user1").build();
    mockUser(user);

    WalletMinimalInfo info = WalletMinimalInfo.builder().name("W1").reference("REF1").build();
    Wallet wallet = Wallet.builder().name("W1").reference("REF1").build();

    when(walletMapper.toDomain(info)).thenReturn(wallet);
    when(walletRepository.existsByReferenceAndUsersContaining("REF1", user)).thenReturn(true);

    assertThrows(IllegalArgumentException.class, () -> walletService.createWallets(List.of(info)));
  }
}
