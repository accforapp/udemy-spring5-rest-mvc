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
import udemy.spring5.restmvc.api.v1.model.VendorDTO;
import udemy.spring5.restmvc.services.VendorService;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class VendorControllerTest {

  @Mock
  VendorService vendorService;

  @InjectMocks
  VendorController vendorController;

  MockMvc mockMvc;

  @BeforeEach
  void setUp() {

    mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();
  }

  @Test
  void getVendors() throws Exception {

    when(vendorService.getAllVendors()).thenReturn(List.of(new VendorDTO(), new VendorDTO()));

    mockMvc.perform(get("/api/v1/vendors").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.vendors", hasSize(2)));
  }
}