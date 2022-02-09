package udemy.spring5.restmvc.controllers.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import udemy.spring5.restmvc.api.v1.model.VendorListDTO;
import udemy.spring5.restmvc.services.VendorService;

@RestController
@RequestMapping("/api/v1/vendors")
public class VendorController {

  private final VendorService vendorService;

  public VendorController(VendorService vendorService) {
    this.vendorService = vendorService;
  }

  @GetMapping
  public VendorListDTO getVendors() {

    return new VendorListDTO(vendorService.getAllVendors());
  }
}
