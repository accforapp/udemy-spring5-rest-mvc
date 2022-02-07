package udemy.spring5.restmvc.controllers.v1;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import udemy.spring5.restmvc.api.v1.model.CustomerDTO;
import udemy.spring5.restmvc.services.CustomerService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

  @Mock
  CustomerService customerService;

  @InjectMocks
  CustomerController customerController;

  MockMvc mockMvc;

  @BeforeEach
  public void setUp() throws Exception {

    mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
  }

  @Test
  public void getAllCustomersTest() throws Exception {

    List<CustomerDTO> customerDTOList = new ArrayList<>(2);

    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setFirstName("First");
    customerDTO.setLastName("Last");

    customerDTOList.add(customerDTO);

    CustomerDTO customerDTO1 = new CustomerDTO();
    customerDTO1.setFirstName("First1");
    customerDTO1.setLastName("Last1");

    customerDTOList.add(customerDTO1);

    when(customerService.getAllCustomers()).thenReturn(customerDTOList);

    mockMvc.perform(get("/api/v1/customers").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.customers", hasSize(2)));
  }

  @Test
  public void getCustomerTest() throws Exception {

    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setFirstName("First");
    customerDTO.setLastName("Last");

    when(customerService.getCustomerById(1L)).thenReturn(customerDTO);

    mockMvc.perform(get("/api/v1/customers/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.firstName", equalTo("First")));
  }

  @Test
  void createNewCustomerTest() throws Exception {

    CustomerDTO returnedCustomerDTO = new CustomerDTO();
    returnedCustomerDTO.setFirstName("John");
    returnedCustomerDTO.setCustomerUrl("/api/v1/customers/1");

    when(customerService.createNewCustomer(any())).thenReturn(returnedCustomerDTO);

    mockMvc.perform(post("/api/v1/customers/new").contentType(MediaType.APPLICATION_JSON)
    .content("{\"firstName\":\"John\"}"))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.firstName", equalTo("John")))
        .andExpect(jsonPath("$.customerUrl", equalTo("/api/v1/customers/1")));
  }
}