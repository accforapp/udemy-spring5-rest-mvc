package udemy.spring5.restmvc.api.v1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {

  private String firstName;
  private String lastName;
  private String customerUrl;
}
