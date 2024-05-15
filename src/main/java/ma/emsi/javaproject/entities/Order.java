package ma.emsi.javaproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @Column(name = "transporterName", nullable = false)
    private String transporterName;
    @Column(name = "transporterPrice", nullable = false)
    private Float transporterPrice;
    @Column(name = "delivery", nullable = false)
    private String delivery;
    @Column(name = "isPaid", nullable = false)
    private boolean isPaid;
    @Column(name = "method", nullable = false)
    private String method;
    @Column(name = "reference", nullable = false)
    private String reference;
    @Column(name = "stripeSessionId", nullable = false)
    private String stripeSessionId;
    @Column(name = "paypalOrderId", nullable = false)
    private String paypalOrderId;
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<RecapDetails> recapDetails;
    public Order addRecapDetail(RecapDetails recapDetail)
    {
        if (!this.recapDetails.contains(recapDetail)) {
            this.recapDetails.add(recapDetail);
            recapDetail.setOrderProduct(this);
        }

        return this;
    }
    public Order removeRecapDetail(RecapDetails recapDetail)
    {
        if (this.recapDetails.remove(recapDetail)) {
            // set the owning side to null (unless already changed)
            if (recapDetail.getOrderProduct() == this) {
                recapDetail.setOrderProduct(null);
            }
        }

        return this;
    }



}
