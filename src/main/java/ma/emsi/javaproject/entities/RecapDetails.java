package ma.emsi.javaproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RecapDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Column(name = "product", nullable = false)
    private String product;
    @Column(name = "price", nullable = false)
    private Float price;
    @Column(name = "totalRecap", nullable = false)
    private String totalRecap;
    @ManyToOne(fetch = FetchType.EAGER)
    private Order orderProduct;
}
