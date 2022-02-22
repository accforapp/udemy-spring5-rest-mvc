package udemy.spring5.restmvc.api.v1.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import udemy.spring5.restmvc.api.v1.model.CategoryDTO;
import udemy.spring5.restmvc.domain.Category;

@Mapper
public interface CategoryMapper {

  CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

  CategoryDTO categoryToCategoryDTO(Category category);
}
