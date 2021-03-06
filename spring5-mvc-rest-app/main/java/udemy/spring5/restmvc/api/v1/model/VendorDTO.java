package udemy.spring5.restmvc.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorDTO {

  private String name;

  @JsonProperty("vendor_url")
  private String vendorUrl;
}
