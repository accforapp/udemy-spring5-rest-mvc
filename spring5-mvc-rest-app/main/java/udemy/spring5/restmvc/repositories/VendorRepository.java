package udemy.spring5.restmvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import udemy.spring5.restmvc.domain.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
