package udemy.spring5.restmvc.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import udemy.spring5.restmvc.domain.Category;
import udemy.spring5.restmvc.domain.Customer;
import udemy.spring5.restmvc.domain.Vendor;
import udemy.spring5.restmvc.repositories.CategoryRepository;
import udemy.spring5.restmvc.repositories.CustomerRepository;
import udemy.spring5.restmvc.repositories.VendorRepository;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

  private final CategoryRepository categoryRepository;
  private final CustomerRepository customerRepository;
  private final VendorRepository vendorRepository;

  public Bootstrap(CategoryRepository categoryRepository,
                   CustomerRepository customerRepository,
                   VendorRepository vendorRepository) {

    this.categoryRepository = categoryRepository;
    this.customerRepository = customerRepository;
    this.vendorRepository = vendorRepository;
  }

  @Override
  public void run(String... args) throws Exception {

    loadCustomers();

    loadCategories();

    loadVendors();
  }

  private void loadCategories() {
    Category fruits = new Category();
    fruits.setName("Fruits");

    Category dried = new Category();
    dried.setName("Dried");

    Category fresh = new Category();
    fresh.setName("Fresh");

    Category exotic = new Category();
    exotic.setName("Exotic");

    Category nuts = new Category();
    nuts.setName("Nuts");

    categoryRepository.save(fruits);
    categoryRepository.save(dried);
    categoryRepository.save(fresh);
    categoryRepository.save(exotic);
    categoryRepository.save(nuts);

    log.info("Categories loaded = " + categoryRepository.count());
  }

  private void loadCustomers() {

    Customer customer1 = new Customer();
    customer1.setFirstName("Michale");
    customer1.setLastName("Weston");

    customerRepository.save(customer1);

    Customer customer2 = new Customer();
    customer2.setFirstName("Sam");
    customer2.setLastName("Axe");

    customerRepository.save(customer2);

    log.info("Customers loaded = " + customerRepository.count());

  }

  private void loadVendors() {

    Vendor vendor1 = new Vendor();
    vendor1.setName("First vendor ltd.");

    vendorRepository.save(vendor1);

    Vendor vendor2 = new Vendor();
    vendor2.setName("Second vendor org.");

    vendorRepository.save(vendor2);

    Vendor vendor3 = new Vendor();
    vendor3.setName("Third vendor corp.");

    vendorRepository.save(vendor3);

    log.info("Vendors loaded = " + vendorRepository.count());
  }
}
