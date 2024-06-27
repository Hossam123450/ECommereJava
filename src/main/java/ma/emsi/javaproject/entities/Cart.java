package ma.emsi.javaproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(fetch = FetchType.EAGER)
    private User user;
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Product> products = new ArrayList<>();
    public void addProduct(Product product){
        product.setQuantity(product.getQuantity()+1);
        products.add(product);
    }
    public void removeProduct(Product product){
        product.setQuantity(product.getQuantity()-1);
        products.remove(product);
    }
    public void removeAll(){
        products.forEach(product -> product.setQuantity(0));
        products.clear();
    }
    public void increase(Integer id){
        for (Product product : products) {
            if (product.getId() == id) {
                product.setQuantity(product.getQuantity()+1);
            }
        }
    }
    public void decrease(Integer id){
        for (Product product : products) {
            if (product.getId() == id) {
                product.setQuantity(product.getQuantity()-1);
            }
        }
    }
    public double getTotal(){
        double total=0.0;
        for (Product product : products) {
            total+=  product.getQuantity()*product.getPrice();
        }
        return total;
    }
//    public Product findProductById(int id) {
//        for (Product product : products) {
//            if (product.getId() == id) {
//                return product;
//            }
//        }
//        return null; // Retourne null si le produit n'est pas trouvé
//    }

}
