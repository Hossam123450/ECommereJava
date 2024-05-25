package ma.emsi.javaproject.repositories;

import jakarta.transaction.Transactional;
import ma.emsi.javaproject.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional
public interface ProductRepository extends JpaRepository<Product,Integer>{
//    List<Product> findByFullName(String name);
//    List<Product> findByFullNameContains(String name);

    Page<Product> findByFullNameContains(String name, PageRequest pageable);
}
