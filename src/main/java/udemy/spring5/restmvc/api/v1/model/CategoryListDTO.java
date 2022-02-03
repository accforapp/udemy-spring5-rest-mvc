package udemy.spring5.restmvc.api.v1.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryListDTO {

  private List<CategoryDTO> categories;
}
