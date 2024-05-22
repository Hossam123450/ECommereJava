package ma.emsi.javaproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @Column(name = "slug", nullable = false)
    private String slug;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "subtitle", nullable = false)
    private String subtitle;
    @Column(name = "price", nullable = false)
    private Float price;
    @Column(name = "quantity")
    private Integer quantity=0;
    @Column(name = "image", nullable = false)
    private String image;
    @UploadableField(mapping = "products", fileNameProperty = "image")
    private File attachmentFile;


}
