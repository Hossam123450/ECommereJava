package ma.emsi.javaproject.repositories;

import jakarta.transaction.Transactional;
import ma.emsi.javaproject.entities.RecapDetails;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface RecapDetailsRepository extends JpaRepository<RecapDetails,Integer> {
}
