package udemy.spring5.restmvc.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import udemy.spring5.restmvc.api.v1.model.VendorDTO;
import udemy.spring5.restmvc.domain.Vendor;
import udemy.spring5.restmvc.repositories.VendorRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VendorServiceImplTest {

  @Mock
  VendorRepository vendorRepository;

  @InjectMocks
  VendorServiceImpl vendorService;

  @Test
  void getAllVendors() {

    Vendor vendor1 = new Vendor();
    vendor1.setName("Vendor One");
    vendor1.setId(1L);

    Vendor vendor2 = new Vendor();
    vendor2.setName("Second Vendor");
    vendor2.setId(2L);

    List<Vendor> vendorList = new ArrayList<>(2);
    vendorList.add(vendor1);
    vendorList.add(vendor2);

    when(vendorRepository.findAll()).thenReturn(vendorList);

    List<VendorDTO> allVendors = vendorService.getAllVendors();

    assertEquals(2, allVendors.size());
  }
}