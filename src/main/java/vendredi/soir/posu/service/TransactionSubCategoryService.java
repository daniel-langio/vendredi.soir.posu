package vendredi.soir.posu.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vendredi.soir.posu.model.TransactionSubCategory;
import vendredi.soir.posu.repository.TransactionSubCategoryRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionSubCategoryService {
    private final TransactionSubCategoryRepository transactionSubCategoryRepository;

    public List<TransactionSubCategory> createSubCategories(List<TransactionSubCategory> subCategories) {
        for (TransactionSubCategory subCategory : subCategories) {
            if (transactionSubCategoryRepository.existsByReference(subCategory.getReference())) {
                throw new IllegalArgumentException("SubCategory with reference " + subCategory.getReference() + " already exists");
            }
        }
        return transactionSubCategoryRepository.saveAll(subCategories);
    }

    public List<TransactionSubCategory> getAllSubCategories() {
        return transactionSubCategoryRepository.findAll();
    }
}
