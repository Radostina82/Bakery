package bg.softuni.Bakery.repository;

import bg.softuni.Bakery.model.dto.ProductDTO;
import bg.softuni.Bakery.model.entities.CategoryEntity;
import bg.softuni.Bakery.model.entities.ProductEntity;
import bg.softuni.Bakery.model.enums.CategoryTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {


    List<ProductEntity> findByCategory(CategoryEntity byCategoryType);
}
