package vendredi.soir.posu.endpoint.rest.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vendredi.soir.posu.endpoint.rest.mapper.WalletMapper;
import vendredi.soir.posu.endpoint.rest.model.Wallet;
import vendredi.soir.posu.endpoint.rest.model.WalletMinimalInfo;
import vendredi.soir.posu.service.WalletService;

@RestController
@RequestMapping("/wallet")
@AllArgsConstructor
public class WalletController {
  private final WalletService walletService;
  private final WalletMapper walletMapper;

  @PostMapping
  public ResponseEntity<List<Wallet>> createWallet(@RequestBody List<WalletMinimalInfo> wallets) {
    try {
      List<Wallet> createdWallets =
          walletService.createWallets(wallets).stream()
              .map(walletMapper::toRest)
              .collect(Collectors.toList());
      return ResponseEntity.status(HttpStatus.CREATED).body(createdWallets);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }

  @GetMapping
  public ResponseEntity<List<Wallet>> getAllWallet() {
    return ResponseEntity.ok(
        walletService.getAllWallets().stream()
            .map(walletMapper::toRest)
            .collect(Collectors.toList()));
  }
}
