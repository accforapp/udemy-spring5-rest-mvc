package udemy.spring5.restmvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import udemy.spring5.restmvc.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

  Category findByName(String name);
}
