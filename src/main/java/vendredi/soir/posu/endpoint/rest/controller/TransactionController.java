package vendredi.soir.posu.endpoint.rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vendredi.soir.posu.model.Transaction;
import vendredi.soir.posu.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<List<Transaction>> recordTransactions(@RequestBody List<Transaction> transactions) {
        List<Transaction> recordedTransactions = transactionService.recordTransactions(transactions);
        return ResponseEntity.status(HttpStatus.CREATED).body(recordedTransactions);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransaction() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }
}
