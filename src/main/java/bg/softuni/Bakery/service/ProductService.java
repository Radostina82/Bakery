package bg.softuni.Bakery.service;

import bg.softuni.Bakery.model.dto.*;

import bg.softuni.Bakery.model.entities.CategoryEntity;
import bg.softuni.Bakery.model.entities.ProductEntity;
import bg.softuni.Bakery.model.enums.CategoryTypeEnum;
import bg.softuni.Bakery.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    public void addProduct(AddProductDTO addProductDTO) {
        ProductEntity productEntity = this.modelMapper.map(addProductDTO, ProductEntity.class);

        CategoryEntity byCategoryType = this.categoryService.findByCategoryType(addProductDTO.getCategory());

        productEntity.setCategory(byCategoryType);

        this.productRepository.save(productEntity);

    }

    public List<ProductDTO> getAllProduct() {

        return productRepository.findAll().stream().map(productEntity -> modelMapper.map(productEntity, ProductDTO.class)).collect(Collectors.toList());

    }

    public ProductDTO findById(Long id) {
        Optional<ProductEntity> byId = productRepository.findById(id);

        return modelMapper.map(byId.get(), ProductDTO.class);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void update(UpdateProductDTO updateProductDTO) {
        ProductEntity productEntity = productRepository.findById(updateProductDTO.getId()).orElse(null);
        CategoryEntity category = categoryService.findByCategoryType(updateProductDTO.getCategory());
        productEntity.setCategory(category);
        productEntity.setImage(updateProductDTO.getImage());
        productEntity.setIngredients(updateProductDTO.getIngredients());
        productEntity.setName(updateProductDTO.getName());
        productEntity.setPrice(updateProductDTO.getPrice());
        productEntity.setWeight(updateProductDTO.getWeight());
        productRepository.save(productEntity);
    }

    public List<ProductDTO> getAllProductBread() {
        CategoryEntity byCategoryType = categoryService.findByCategoryType(CategoryTypeEnum.BREAD);
        List<ProductEntity> byCategory = productRepository.findByCategory(categoryService.findByCategoryType(CategoryTypeEnum.BREAD));
        return byCategory.stream().map(productEntity -> modelMapper.map(productEntity, ProductDTO.class)).collect(Collectors.toList());//productRepository.findByCategory(categoryService.findByCategoryType(CategoryTypeEnum.BREAD));
    }
}
