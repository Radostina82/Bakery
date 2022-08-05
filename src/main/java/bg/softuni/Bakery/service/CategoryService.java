package bg.softuni.Bakery.service;

import bg.softuni.Bakery.model.entities.CategoryEntity;
import bg.softuni.Bakery.model.enums.CategoryTypeEnum;
import bg.softuni.Bakery.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void initCategory(){
        if (this.categoryRepository.count() != 0){
            return;
        }

        Arrays.stream(CategoryTypeEnum.values()).forEach(categoryEnum ->{
            CategoryEntity category = new CategoryEntity();
            category.setCategoryType(categoryEnum);
            this.categoryRepository.save(category);
        });
    }

    public CategoryEntity findByCategoryType(CategoryTypeEnum category) {
        return this.categoryRepository.findByCategoryType(category).orElse(null);
    }
}
