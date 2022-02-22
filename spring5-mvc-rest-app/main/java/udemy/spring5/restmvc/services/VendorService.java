package udemy.spring5.restmvc.services;

import udemy.spring5.restmvc.api.v1.model.VendorDTO;

import java.util.List;

public interface VendorService {

  List<VendorDTO> getAllVendors();

  VendorDTO saveNewVendor(VendorDTO vendorDTO);

  void deleteVendor(Long id);

  VendorDTO getVendor(Long id);

}
