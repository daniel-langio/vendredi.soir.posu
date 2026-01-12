package vendredi.soir.posu.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vendredi.soir.posu.endpoint.rest.mapper.TransactionCategoryMapper;
import vendredi.soir.posu.endpoint.rest.model.TransactionCategoryMinimalInfo;
import vendredi.soir.posu.model.TransactionCategory;
import vendredi.soir.posu.repository.TransactionCategoryRepository;

@Service
@AllArgsConstructor
public class TransactionCategoryService {
  private final TransactionCategoryRepository transactionCategoryRepository;
  private final TransactionCategoryMapper transactionCategoryMapper;

  public List<TransactionCategory> createCategories(
      List<TransactionCategoryMinimalInfo> categories) {
    List<TransactionCategory> toSave =
        categories.stream()
            .map(transactionCategoryMapper::toDomain)
            .peek(
                category -> {
                  if (transactionCategoryRepository.existsByReference(category.getReference())) {
                    throw new IllegalArgumentException(
                        "Category with reference " + category.getReference() + " already exists");
                  }
                })
            .collect(Collectors.toList());
    return transactionCategoryRepository.saveAll(toSave);
  }

  public List<TransactionCategory> getAllCategories() {
    return transactionCategoryRepository.findAll();
  }
}
