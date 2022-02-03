package udemy.spring5.restmvc.services;

import udemy.spring5.restmvc.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {

  List<CategoryDTO> getAllCategories();

  CategoryDTO getCategoryByName(String name);
}
