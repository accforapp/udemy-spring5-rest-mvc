package udemy.spring5.restmvc.services;

import udemy.spring5.restmvc.api.v1.model.VendorDTO;
import udemy.spring5.restmvc.domain.Vendor;

import java.util.List;

public interface VendorService {

  List<VendorDTO> getAllVendors();

}
