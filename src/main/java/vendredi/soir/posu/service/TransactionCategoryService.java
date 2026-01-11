package vendredi.soir.posu.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vendredi.soir.posu.model.TransactionCategory;
import vendredi.soir.posu.repository.TransactionCategoryRepository;

@Service
@AllArgsConstructor
public class TransactionCategoryService {
  private final TransactionCategoryRepository transactionCategoryRepository;

  public List<TransactionCategory> createCategories(List<TransactionCategory> categories) {
    for (TransactionCategory category : categories) {
      if (transactionCategoryRepository.existsByReference(category.getReference())) {
        throw new IllegalArgumentException(
            "Category with reference " + category.getReference() + " already exists");
      }
    }
    return transactionCategoryRepository.saveAll(categories);
  }

  public List<TransactionCategory> getAllCategories() {
    return transactionCategoryRepository.findAll();
  }
}
