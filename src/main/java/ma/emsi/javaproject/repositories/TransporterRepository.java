package ma.emsi.javaproject.repositories;

import jakarta.transaction.Transactional;
import ma.emsi.javaproject.entities.Transporter;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface TransporterRepository extends JpaRepository<Transporter,Integer> {
}