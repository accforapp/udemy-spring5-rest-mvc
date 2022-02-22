package udemy.spring5.restmvc.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import udemy.spring5.restmvc.api.v1.model.CategoryDTO;
import udemy.spring5.restmvc.api.v1.model.CategoryListDTO;
import udemy.spring5.restmvc.services.CategoryService;

@Controller
@RequestMapping("/api/v1/categories")
public class CategoryController {

  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  public ResponseEntity<CategoryListDTO> getAllCategories() {

    CategoryListDTO categoryListDTO = new CategoryListDTO();
    categoryListDTO.setCategories(categoryService.getAllCategories());

    return new ResponseEntity<>(categoryListDTO, HttpStatus.OK);
  }

  @GetMapping("/{name}")
  public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name) {

//    CategoryListDTO categoryListDTO = new CategoryListDTO();
//
//    List<CategoryDTO> categoryList = new ArrayList<>();
//
//    categoryList.add(categoryService.getCategoryByName(name));
//
//    categoryListDTO.setCategories(categoryList);

    return new ResponseEntity<>(categoryService.getCategoryByName(name), HttpStatus.OK);
  }
}
