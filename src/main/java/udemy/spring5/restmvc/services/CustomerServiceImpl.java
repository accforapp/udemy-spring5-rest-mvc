package udemy.spring5.restmvc.services;

import org.springframework.stereotype.Service;
import udemy.spring5.restmvc.api.v1.mappers.CustomerMapper;
import udemy.spring5.restmvc.api.v1.model.CustomerDTO;
import udemy.spring5.restmvc.domain.Customer;
import udemy.spring5.restmvc.repositories.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;

  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
    this.customerMapper = CustomerMapper.INSTANCE;
  }

  @Override
  public List<CustomerDTO> getAllCustomers() {

    return customerRepository.findAll()
        .stream()
        .map(customerMapper::customerToCustomerDTO)
        .collect(Collectors.toList());
  }

  @Override
  public CustomerDTO getCustomerById(Long id) {

    return customerRepository.findById(id)
        .map(customerMapper::customerToCustomerDTO)
        .orElse(null);
  }

  @Override
  public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {

    Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

    Customer savedCustomer = customerRepository.save(customer);

    CustomerDTO savedCustomerDTO = customerMapper.customerToCustomerDTO(savedCustomer);
    savedCustomerDTO.setCustomerUrl("/api/v1/customers/" + savedCustomer.getId());

    return savedCustomerDTO;
  }
}
