package ma.emsi.javaproject.repositories;

import ma.emsi.javaproject.entities.Cart;
import ma.emsi.javaproject.entities.Recommandation;
import ma.emsi.javaproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationRepository extends JpaRepository<Recommandation,Integer> {
    Recommandation findByUser(User user);
    Recommandation findByCart(Cart cart);

}
