package ma.emsi.javaproject.repositories;

import jakarta.transaction.Transactional;
import ma.emsi.javaproject.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface AddressRepository extends JpaRepository<Address,Integer> {
}
