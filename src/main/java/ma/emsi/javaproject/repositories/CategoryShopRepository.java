package ma.emsi.javaproject.repositories;

import jakarta.transaction.Transactional;
import ma.emsi.javaproject.entities.CategoryShop;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface CategoryShopRepository extends JpaRepository<CategoryShop,Integer> {
}
