package ma.emsi.javaproject.repositories;

import jakarta.transaction.Transactional;
import ma.emsi.javaproject.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface OrderRepository extends JpaRepository<Order,Integer> {
}
