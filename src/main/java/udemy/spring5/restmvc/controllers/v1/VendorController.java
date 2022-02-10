package udemy.spring5.restmvc.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import udemy.spring5.restmvc.api.v1.model.VendorDTO;
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

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public VendorDTO saveNewVendor(@RequestBody VendorDTO vendorDTO) {

    return vendorService.saveNewVendor(vendorDTO);
  }

  @DeleteMapping("/{id}")
  public void deleteVendor(@PathVariable Long id) {

    vendorService.deleteVendor(id);
  }

  @GetMapping("/{id}")
  public VendorDTO getVendor(@PathVariable Long id) {

    return vendorService.getVendor(id);
  }
}
