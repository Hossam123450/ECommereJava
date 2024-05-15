package ma.emsi.javaproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "company", nullable = false)
    private String company;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "postalCode", nullable = false)
    private String postalCode;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "city", nullable = false)
    private String city;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    public String toString()
    {
        return title + "[-br]" +
            address + "[-br]" +
            city + " - " +
            country;
    }
}
