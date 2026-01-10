package vendredi.soir.posu.endpoint.rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vendredi.soir.posu.model.Wallet;
import vendredi.soir.posu.service.WalletService;

import java.util.List;

@RestController
@RequestMapping("/wallet")
@AllArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @PostMapping
    public ResponseEntity<List<Wallet>> createWallet(@RequestBody List<Wallet> wallets) {
        try {
            List<Wallet> createdWallets = walletService.createWallets(wallets);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdWallets);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Wallet>> getAllWallet() {
        return ResponseEntity.ok(walletService.getAllWallets());
    }
}
