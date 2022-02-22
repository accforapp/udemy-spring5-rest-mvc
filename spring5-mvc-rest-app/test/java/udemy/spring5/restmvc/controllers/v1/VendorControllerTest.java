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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

  @Test
  void saveNewVendor() throws Exception {

    VendorDTO vendorDTO = new VendorDTO();
    vendorDTO.setName("Test Vendor");
    vendorDTO.setVendorUrl("/api/v1/vendors/1");

    when(vendorService.saveNewVendor(any(VendorDTO.class))).thenReturn(vendorDTO);

    mockMvc.perform(post("/api/v1/vendors")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"Test Vendor\"}"))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name", equalTo("Test Vendor")))
        .andExpect(jsonPath("$.vendor_url", equalTo("/api/v1/vendors/1")));
  }

  @Test
  void deleteVendor() throws Exception {

    mockMvc.perform(delete("/api/v1/vendors/1"))
        .andExpect(status().isOk());

    verify(vendorService).deleteVendor(1L);
  }

  @Test
  void getVendor() throws Exception {

    VendorDTO returned = new VendorDTO();
    returned.setName("Test Vendor");
    returned.setVendorUrl("/api/v1/vendors/1");

    when(vendorService.getVendor(1L)).thenReturn(returned);

    mockMvc.perform(get("/api/v1/vendors/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", equalTo("Test Vendor")))
        .andExpect(jsonPath("$.vendor_url", equalTo("/api/v1/vendors/1")));
  }
}