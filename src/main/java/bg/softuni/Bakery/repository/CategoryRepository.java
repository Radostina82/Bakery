package bg.softuni.Bakery.repository;

import bg.softuni.Bakery.model.entities.CategoryEntity;
import bg.softuni.Bakery.model.enums.CategoryTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByCategoryType(CategoryTypeEnum category);
}
