package udemy.spring5.restmvc.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import udemy.spring5.restmvc.api.v1.mappers.CustomerMapper;
import udemy.spring5.restmvc.api.v1.model.CustomerDTO;
import udemy.spring5.restmvc.domain.Customer;
import udemy.spring5.restmvc.repositories.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

  @Mock
  CustomerRepository customerRepository;

  @InjectMocks
  CustomerServiceImpl customerService;

  @Test
  void getAllCustomers() {

    List<Customer> customers = new ArrayList<>(2);

    Customer customer1 = new Customer();
    customer1.setId(1L);

    Customer customer2 = new Customer();
    customer2.setId(2L);

    customers.add(customer1);
    customers.add(customer2);

    when(customerRepository.findAll()).thenReturn(customers);

    List<CustomerDTO> returnedCustomers = customerService.getAllCustomers();

    assertNotNull(returnedCustomers);
    assertEquals(2, returnedCustomers.size());

  }

  @Test
  void getCustomerById() {

    Customer customer = new Customer();
    customer.setId(1L);
    customer.setFirstName("First");

    when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

    CustomerDTO customerDTO = customerService.getCustomerById(1L);

    assertNotNull(customerDTO);
    assertEquals("First", customerDTO.getFirstName());
  }

  @Test
  void createNewCustomer() {

    Customer customer = new Customer();
    customer.setId(1L);
    customer.setFirstName("First");

    when(customerRepository.save(any())).thenReturn(customer);

    CustomerDTO customerDTO = CustomerMapper.INSTANCE.customerToCustomerDTO(customer);

    CustomerDTO newCustomer = customerService.createNewCustomer(customerDTO);

    assertNotNull(newCustomer);
    assertEquals("First", newCustomer.getFirstName());
  }

  @Test
  void saveCustomerById() {

    Customer customer = new Customer();
    customer.setId(1L);
    customer.setFirstName("John");

    when(customerRepository.save(any())).thenReturn(customer);

    CustomerDTO toSave = CustomerMapper.INSTANCE.customerToCustomerDTO(customer);

    CustomerDTO saved = customerService.saveCustomerById(1L, toSave);

    assertNotNull(saved);
    assertEquals("John", saved.getFirstName());
    assertEquals("/api/v1/customers/1", saved.getCustomerUrl());
  }
}