package udemy.spring5.restmvc.services;

import udemy.spring5.restmvc.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

  List<CustomerDTO> getAllCustomers();

  CustomerDTO getCustomerById(Long id);

  CustomerDTO createNewCustomer(CustomerDTO customerDTO);

  CustomerDTO saveCustomerById(Long id, CustomerDTO customerDTO);

  CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);

  void deleteById(Long id);
}
