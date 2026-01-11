package vendredi.soir.posu.endpoint.rest.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vendredi.soir.posu.endpoint.rest.mapper.TransactionSubCategoryMapper;
import vendredi.soir.posu.endpoint.rest.model.TransactionSubCategory;
import vendredi.soir.posu.endpoint.rest.model.TransactionSubCategoryMinimalInfo;
import vendredi.soir.posu.service.TransactionSubCategoryService;

@RestController
@RequestMapping("/transaction/subcategory")
@AllArgsConstructor
public class TransactionSubCategoryController {
  private final TransactionSubCategoryService transactionSubCategoryService;
  private final TransactionSubCategoryMapper transactionSubCategoryMapper;

  @PostMapping
  public ResponseEntity<List<TransactionSubCategory>> createTransactionSubCategories(
      @RequestBody List<TransactionSubCategoryMinimalInfo> subCategories) {
    try {
      List<TransactionSubCategory> createdSubCategories =
          transactionSubCategoryService.createSubCategories(subCategories).stream()
              .map(transactionSubCategoryMapper::toRest)
              .collect(Collectors.toList());
      return ResponseEntity.status(HttpStatus.CREATED).body(createdSubCategories);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }

  @GetMapping
  public ResponseEntity<List<TransactionSubCategory>> getAllTransactionSubCategory() {
    return ResponseEntity.ok(
        transactionSubCategoryService.getAllSubCategories().stream()
            .map(transactionSubCategoryMapper::toRest)
            .collect(Collectors.toList()));
  }
}
