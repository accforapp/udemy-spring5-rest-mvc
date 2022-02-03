package udemy.spring5.restmvc.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import udemy.spring5.restmvc.domain.Category;
import udemy.spring5.restmvc.repositories.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

  @Mock
  CategoryRepository categoryRepository;

  @InjectMocks
  CategoryServiceImpl categoryService;

  @Test
  void getAllCategories() {

    List<Category> categories = new ArrayList<>(3);
    categories.add(new Category());
    categories.add(new Category());
    categories.add(new Category());

    when(categoryRepository.findAll()).thenReturn(categories);

    assertEquals(3, categoryService.getAllCategories().size());
  }

  @Test
  void getCategoryByName() {

    Category test = new Category();
    test.setName("test");

    when(categoryRepository.findByName(anyString())).thenReturn(test);

    assertEquals("test", categoryService.getCategoryByName("test").getName());
  }
}