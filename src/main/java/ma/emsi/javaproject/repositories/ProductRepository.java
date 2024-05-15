package ma.emsi.javaproject.repositories;

import jakarta.transaction.Transactional;
import ma.emsi.javaproject.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface ProductRepository extends JpaRepository<Product,Integer>{
}
