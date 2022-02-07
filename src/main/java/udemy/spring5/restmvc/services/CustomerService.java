package udemy.spring5.restmvc.services;

import udemy.spring5.restmvc.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

  List<CustomerDTO> getAllCustomers();

  CustomerDTO getCustomerById(Long id);

  CustomerDTO createNewCustomer(CustomerDTO customerDTO);
}
