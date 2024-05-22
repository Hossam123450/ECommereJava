package ma.emsi.javaproject.repositories;

import jakarta.transaction.Transactional;
import ma.emsi.javaproject.entities.Cart;
import ma.emsi.javaproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface CartRepository extends JpaRepository<Cart,Integer> {
    Cart findByUser(User user);
}
