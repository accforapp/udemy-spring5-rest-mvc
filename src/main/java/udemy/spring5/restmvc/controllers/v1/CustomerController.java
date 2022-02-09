package udemy.spring5.restmvc.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import udemy.spring5.restmvc.api.v1.model.CustomerDTO;
import udemy.spring5.restmvc.api.v1.model.CustomerListDTO;
import udemy.spring5.restmvc.services.CustomerService;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping
  public ResponseEntity<CustomerListDTO> getAllCustomers() {

    return new ResponseEntity<>(new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id) {

    return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
  }

  @PostMapping("/new")
  public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {

    return new ResponseEntity<>(customerService.createNewCustomer(customerDTO), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {

    return new ResponseEntity<>(customerService.saveCustomerById(id, customerDTO), HttpStatus.OK);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {

    return new ResponseEntity<>(customerService.patchCustomer(id, customerDTO), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
    customerService.deleteById(id);

    return ResponseEntity.ok().build();
  }
}
