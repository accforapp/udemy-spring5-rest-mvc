package udemy.spring5.restmvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import udemy.spring5.restmvc.domain.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
