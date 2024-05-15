package ma.emsi.javaproject.repositories;

import jakarta.transaction.Transactional;
import ma.emsi.javaproject.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface ContactRepository extends JpaRepository<Contact,Integer> {
}
