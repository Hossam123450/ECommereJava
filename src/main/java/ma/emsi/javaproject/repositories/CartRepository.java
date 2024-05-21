package ma.emsi.javaproject.repositories;

import jakarta.transaction.Transactional;
import ma.emsi.javaproject.entities.Cart;
import ma.emsi.javaproject.entities.Order;
import ma.emsi.javaproject.entities.Product;
import ma.emsi.javaproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional
public interface CartRepository extends JpaRepository<Cart,Integer> {
    Cart findByUser(User user);
}
