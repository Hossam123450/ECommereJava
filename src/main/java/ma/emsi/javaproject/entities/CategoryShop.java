package ma.emsi.javaproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CategoryShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "slug", nullable = false)
    private String slug;
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Product> products;
    public String toString()
    {
        return name;
    }
    public CategoryShop addProduct(Product product)
    {
        if (this.products.contains(product)) {
            this.products.add(product);
            product.setCategory(this);
        }

        return this;
    }
    public CategoryShop removeProduct(Product product)
    {
        if (this.products.remove(product)) {
            // set the owning side to null (unless already changed)
            if (product.getCategory() == this) {
                product.setCategory(null);
            }
        }

        return this;
    }
}
