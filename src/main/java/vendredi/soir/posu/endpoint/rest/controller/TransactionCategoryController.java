package vendredi.soir.posu.endpoint.rest.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vendredi.soir.posu.endpoint.rest.mapper.TransactionCategoryMapper;
import vendredi.soir.posu.endpoint.rest.model.TransactionCategory;
import vendredi.soir.posu.endpoint.rest.model.TransactionCategoryMinimalInfo;
import vendredi.soir.posu.service.TransactionCategoryService;

@RestController
@RequestMapping("/transaction/category")
@AllArgsConstructor
public class TransactionCategoryController {
  private final TransactionCategoryService transactionCategoryService;
  private final TransactionCategoryMapper transactionCategoryMapper;

  @PostMapping
  public ResponseEntity<List<TransactionCategory>> createTransactionCategory(
      @RequestBody List<TransactionCategoryMinimalInfo> categories) {
    try {
      List<TransactionCategory> createdCategories =
          transactionCategoryService.createCategories(categories).stream()
              .map(transactionCategoryMapper::toRest)
              .collect(Collectors.toList());
      return ResponseEntity.status(HttpStatus.CREATED).body(createdCategories);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }

  @GetMapping
  public ResponseEntity<List<TransactionCategory>> getAllTransactionCategory() {
    return ResponseEntity.ok(
        transactionCategoryService.getAllCategories().stream()
            .map(transactionCategoryMapper::toRest)
            .collect(Collectors.toList()));
  }
}
