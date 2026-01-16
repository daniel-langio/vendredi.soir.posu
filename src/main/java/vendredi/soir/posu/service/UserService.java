package vendredi.soir.posu.service;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vendredi.soir.posu.endpoint.rest.model.LoginRequest;
import vendredi.soir.posu.endpoint.rest.model.RegisterRequest;
import vendredi.soir.posu.model.User;
import vendredi.soir.posu.model.Wallet;
import vendredi.soir.posu.model.WalletType;
import vendredi.soir.posu.repository.UserRepository;
import vendredi.soir.posu.repository.WalletRepository;

@Service
@AllArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final WalletRepository walletRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public User register(RegisterRequest request) {
    if (userRepository.existsByUsername(request.getUsername())) {
      throw new IllegalArgumentException("Username already exists");
    }
    if (userRepository.existsByEmail(request.getEmail())) {
      throw new IllegalArgumentException("Email already exists");
    }

    User user =
        User.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .apiKey(UUID.randomUUID().toString())
            .build();

    user = userRepository.save(user);

    Wallet defaultWallet =
        Wallet.builder()
            .name("default's " + user.getUsername() + " wallet")
            .reference("DEFAULT_WALLET_" + user.getUsername().toUpperCase() + "_" + UUID.randomUUID().toString().substring(0, 8))
            .type(WalletType.CASH)
            .users(List.of(user))
            .build();

    walletRepository.save(defaultWallet);

    return user;
  }

  public User login(LoginRequest request) {
    return userRepository
        .findByUsername(request.getUsername())
        .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
        .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
  }
}
