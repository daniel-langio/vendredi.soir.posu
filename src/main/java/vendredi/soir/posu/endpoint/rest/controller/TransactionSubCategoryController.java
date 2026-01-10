package vendredi.soir.posu.endpoint.rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vendredi.soir.posu.model.TransactionSubCategory;
import vendredi.soir.posu.service.TransactionSubCategoryService;

import java.util.List;

@RestController
@RequestMapping("/transaction/subcategory")
@AllArgsConstructor
public class TransactionSubCategoryController {
    private final TransactionSubCategoryService transactionSubCategoryService;

    @PostMapping
    public ResponseEntity<List<TransactionSubCategory>> createTransactionSubCategories(@RequestBody List<TransactionSubCategory> subCategories) {
        try {
            List<TransactionSubCategory> createdSubCategories = transactionSubCategoryService.createSubCategories(subCategories);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSubCategories);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TransactionSubCategory>> getAllTransactionSubCategory() {
        return ResponseEntity.ok(transactionSubCategoryService.getAllSubCategories());
    }
}
