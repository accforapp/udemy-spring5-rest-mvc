package udemy.spring5.restmvc.services;

import org.springframework.stereotype.Service;
import udemy.spring5.restmvc.api.v1.mappers.CategoryMapper;
import udemy.spring5.restmvc.api.v1.model.CategoryDTO;
import udemy.spring5.restmvc.repositories.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

  public CategoryServiceImpl(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public List<CategoryDTO> getAllCategories() {
    return categoryRepository.findAll()
        .stream()
        .map(categoryMapper::categoryToCategoryDTO)
        .collect(Collectors.toList());
  }

  @Override
  public CategoryDTO getCategoryByName(String name) {
    return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
  }
}
