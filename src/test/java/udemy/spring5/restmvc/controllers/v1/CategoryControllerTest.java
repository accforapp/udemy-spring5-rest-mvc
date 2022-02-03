package udemy.spring5.restmvc.controllers.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import udemy.spring5.restmvc.api.v1.model.CategoryDTO;
import udemy.spring5.restmvc.services.CategoryService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

  @Mock
  CategoryService categoryService;

  @InjectMocks
  CategoryController categoryController;

  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
  }

  @Test
  void testListCategories() throws Exception {

    CategoryDTO category1 = new CategoryDTO();
    category1.setId(1L);
    category1.setName("Jim");

    CategoryDTO category2 = new CategoryDTO();
    category2.setId(2L);
    category2.setName("Bob");

    List<CategoryDTO> categoryDTOS = new ArrayList<>(2);
    categoryDTOS.add(category1);
    categoryDTOS.add(category2);

    when(categoryService.getAllCategories()).thenReturn(categoryDTOS);

    mockMvc.perform(get("/api/v1/categories")
    .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.categories", hasSize(2)));
  }

  @Test
  void testGetByNameCategories() throws Exception {

    CategoryDTO categoryDTO = new CategoryDTO();
    categoryDTO.setId(1L);
    categoryDTO.setName("Jim");

    when(categoryService.getCategoryByName("Jim")).thenReturn(categoryDTO);

    mockMvc.perform(get("/api/v1/categories/Jim")
    .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", equalTo("Jim")));
  }

}