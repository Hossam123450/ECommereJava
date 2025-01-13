package ma.emsi.javaproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recommandation")
public class Recommandation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(fetch = FetchType.EAGER)
    private User user;
    @OneToOne(fetch = FetchType.EAGER)
    private Cart cart;
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Product> products = new ArrayList<>();
    public void addProduct(Product product){
            products.add(product);
    }
}
