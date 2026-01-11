package vendredi.soir.posu.endpoint.rest.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vendredi.soir.posu.endpoint.rest.mapper.TransactionMapper;
import vendredi.soir.posu.endpoint.rest.model.Transaction;
import vendredi.soir.posu.endpoint.rest.model.TransactionMinimalInfo;
import vendredi.soir.posu.service.TransactionService;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {
  private final TransactionService transactionService;
  private final TransactionMapper transactionMapper;

  @PostMapping
  public ResponseEntity<List<Transaction>> recordTransactions(
      @RequestBody List<TransactionMinimalInfo> transactions) {
    List<Transaction> recordedTransactions =
        transactionService.recordTransactions(transactions).stream()
            .map(transactionMapper::toRest)
            .collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.CREATED).body(recordedTransactions);
  }

  @GetMapping
  public ResponseEntity<List<Transaction>> getAllTransaction() {
    return ResponseEntity.ok(
        transactionService.getAllTransactions().stream()
            .map(transactionMapper::toRest)
            .collect(Collectors.toList()));
  }
}
