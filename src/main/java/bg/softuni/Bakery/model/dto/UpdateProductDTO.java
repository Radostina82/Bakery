package bg.softuni.Bakery.model.dto;

import bg.softuni.Bakery.model.enums.CategoryTypeEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class UpdateProductDTO {
    protected Long id;
    @NotNull
    private CategoryTypeEnum category;
    @NotEmpty
    private String name;
    @NotEmpty
    private String ingredients;
    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    @Positive
    private Double weight;
    @NotEmpty
    private String image;

    public UpdateProductDTO() {
    }

    public CategoryTypeEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryTypeEnum category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
