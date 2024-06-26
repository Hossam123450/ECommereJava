package ma.emsi.javaproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title", nullable = false)
    private String fullName;
    @Column(name = "subtitle", nullable = false)
    private String subtitle;
    @Column(name = "price", nullable = false)
    private Float price;
    @Column(name = "qteStock", nullable = false)
    private Integer qteStock=0;
    @Column(name = "quantity", nullable = false)
    private Integer quantity=0;
    @Column(name = "image", nullable = false)
    private String image;
    @Transient
    @UploadableField(mapping = "products", fileNameProperty = "image")
    private MultipartFile attachmentFile;


}
