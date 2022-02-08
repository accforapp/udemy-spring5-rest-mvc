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
        .map(customer -> {
          CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
          customerDTO.setCustomerUrl("/api/v1/customers/" + customer.getId());

          return customerDTO;
        })
        .collect(Collectors.toList());
  }

  @Override
  public CustomerDTO getCustomerById(Long id) {

    CustomerDTO customerDTO = customerRepository.findById(id)
        .map(customerMapper::customerToCustomerDTO)
        .orElseThrow(RuntimeException::new);

    customerDTO.setCustomerUrl("/api/v1/customers/" + id);

    return customerDTO;
  }

  @Override
  public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {

    Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

    return saveAndReturn(customer);
  }

  @Override
  public CustomerDTO saveCustomerById(Long id, CustomerDTO customerDTO) {

    Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
    customer.setId(id);

    return saveAndReturn(customer);
  }

  @Override
  public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {

    Customer fromDB = customerRepository.findById(id).orElseThrow(RuntimeException::new);

    if (customerDTO.getFirstName() != null) {
      fromDB.setFirstName(customerDTO.getFirstName());
    }

    if (customerDTO.getLastName() != null) {
      fromDB.setLastName(customerDTO.getLastName());
    }

    CustomerDTO saved = customerMapper.customerToCustomerDTO(customerRepository.save(fromDB));
    saved.setCustomerUrl("/api/v1/customers/" + id);

    return saved;
  }

  private CustomerDTO saveAndReturn(Customer customer) {

    Customer savedCustomer = customerRepository.save(customer);

    CustomerDTO savedCustomerDTO = customerMapper.customerToCustomerDTO(savedCustomer);
    savedCustomerDTO.setCustomerUrl("/api/v1/customers/" + savedCustomer.getId());

    return savedCustomerDTO;
  }
}
