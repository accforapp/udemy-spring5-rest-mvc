package udemy.spring5.restmvc.api.v1.mappers;

import org.junit.jupiter.api.Test;
import udemy.spring5.restmvc.api.v1.model.CategoryDTO;
import udemy.spring5.restmvc.domain.Category;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryMapperTest {

  public static final String NAME = "Joe";
  public static final long ID = 1L;
  CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

  @Test
  void categoryToCategoryDTOTest() {

    Category category = new Category();
    category.setName(NAME);
    category.setId(ID);

    CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

    assertEquals(ID, categoryDTO.getId());
    assertEquals(NAME, categoryDTO.getName());
  }
}