package udemy.spring5.restmvc.services;

import org.springframework.stereotype.Service;
import udemy.spring5.restmvc.api.v1.mappers.VendorMapper;
import udemy.spring5.restmvc.api.v1.model.VendorDTO;
import udemy.spring5.restmvc.repositories.VendorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

  private final VendorRepository vendorRepository;
  private final VendorMapper vendorMapper;

  public VendorServiceImpl(VendorRepository vendorRepository) {
    this.vendorRepository = vendorRepository;
    this.vendorMapper = VendorMapper.INSTANCE;
  }

  @Override
  public List<VendorDTO> getAllVendors() {

    return vendorRepository.findAll().stream()
        .map(vendor -> {
          VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
          vendorDTO.setVendorUrl("/api/v1/vendors" + vendor.getId());
          return vendorDTO;
        })
        .collect(Collectors.toList());
  }
}
