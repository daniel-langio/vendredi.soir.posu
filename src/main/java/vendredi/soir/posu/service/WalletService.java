package vendredi.soir.posu.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vendredi.soir.posu.model.Wallet;
import vendredi.soir.posu.repository.WalletRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;

    public List<Wallet> createWallets(List<Wallet> wallets) {
        for (Wallet wallet : wallets) {
            if (walletRepository.existsByReference(wallet.getReference())) {
                throw new IllegalArgumentException("Wallet with reference " + wallet.getReference() + " already exists");
            }
        }
        return walletRepository.saveAll(wallets);
    }

    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }
}
