package udemy.spring5.restmvc.services;

import org.springframework.stereotype.Service;
import udemy.spring5.restmvc.api.v1.mappers.VendorMapper;
import udemy.spring5.restmvc.api.v1.model.VendorDTO;
import udemy.spring5.restmvc.domain.Vendor;
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
        .map(this::getVendorDTO)
        .collect(Collectors.toList());
  }

  @Override
  public VendorDTO saveNewVendor(VendorDTO vendorDTO) {
    Vendor saved = vendorRepository.save(vendorMapper.vendorDTOToVendor(vendorDTO));

    VendorDTO savedVendorDTO = vendorMapper.vendorToVendorDTO(saved);
    savedVendorDTO.setVendorUrl("/api/v1/vendors/" + saved.getId());

    return savedVendorDTO;
  }

  @Override
  public void deleteVendor(Long id) {

    vendorRepository.deleteById(id);
  }

  @Override
  public VendorDTO getVendor(Long id) {

    return vendorRepository.findById(id)
        .map(this::getVendorDTO)
        .orElseThrow(ResourceNotFoundException::new);
  }

  private VendorDTO getVendorDTO(Vendor vendor) {

    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
    vendorDTO.setVendorUrl("/api/v1/vendors/" + vendor.getId());

    return vendorDTO;

  }
}
