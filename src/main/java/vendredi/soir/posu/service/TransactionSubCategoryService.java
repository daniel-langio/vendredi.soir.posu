package vendredi.soir.posu.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vendredi.soir.posu.endpoint.rest.mapper.TransactionSubCategoryMapper;
import vendredi.soir.posu.endpoint.rest.model.TransactionSubCategoryMinimalInfo;
import vendredi.soir.posu.model.TransactionCategory;
import vendredi.soir.posu.model.TransactionSubCategory;
import vendredi.soir.posu.repository.TransactionCategoryRepository;
import vendredi.soir.posu.repository.TransactionSubCategoryRepository;

@Service
@AllArgsConstructor
public class TransactionSubCategoryService {
  private final TransactionSubCategoryRepository transactionSubCategoryRepository;
  private final TransactionCategoryRepository transactionCategoryRepository;
  private final TransactionSubCategoryMapper transactionSubCategoryMapper;

  public List<TransactionSubCategory> createSubCategories(
      List<TransactionSubCategoryMinimalInfo> subCategories) {
    List<TransactionSubCategory> toSave =
        subCategories.stream()
            .map(
                subCategory -> {
                  if (transactionSubCategoryRepository.existsByReference(
                      subCategory.getReference())) {
                    throw new IllegalArgumentException(
                        "SubCategory with reference "
                            + subCategory.getReference()
                            + " already exists");
                  }
                  TransactionCategory category =
                      transactionCategoryRepository
                          .findByReference(subCategory.getCategoryReference())
                          .orElseThrow(
                              () ->
                                  new IllegalArgumentException(
                                      "Category with reference "
                                          + subCategory.getCategoryReference()
                                          + " does not exist"));
                  return transactionSubCategoryMapper.toDomain(subCategory, category);
                })
            .collect(Collectors.toList());
    return transactionSubCategoryRepository.saveAll(toSave);
  }

  public List<TransactionSubCategory> getAllSubCategories() {
    return transactionSubCategoryRepository.findAll();
  }
}
