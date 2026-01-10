package vendredi.soir.posu.endpoint.rest.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vendredi.soir.posu.model.TransactionCategory;
import vendredi.soir.posu.service.TransactionCategoryService;

@RestController
@RequestMapping("/transaction/category")
@AllArgsConstructor
public class TransactionCategoryController {
  private final TransactionCategoryService transactionCategoryService;

  @PostMapping
  public ResponseEntity<List<TransactionCategory>> createTransactionCategory(
      @RequestBody List<TransactionCategory> categories) {
    try {
      List<TransactionCategory> createdCategories =
          transactionCategoryService.createCategories(categories);
      return ResponseEntity.status(HttpStatus.CREATED).body(createdCategories);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }

  @GetMapping
  public ResponseEntity<List<TransactionCategory>> getAllTransactionCategory() {
    return ResponseEntity.ok(transactionCategoryService.getAllCategories());
  }
}
