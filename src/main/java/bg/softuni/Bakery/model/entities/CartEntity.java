package bg.softuni.Bakery.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "cars")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private UserEntity bayer;
    @OneToMany
    private List<ProductEntity> products;

    public CartEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getBayer() {
        return bayer;
    }

    public void setBayer(UserEntity bayer) {
        this.bayer = bayer;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}
