package vn.edu.hcmuaf.fit.efootwearspringboot.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.category.CategoryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Category;

import java.util.List;

@Mapper(componentModel = "spring")
@Component("categoryMapper")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    //    CategoryDto toDto(Category category);
//
//    @InheritInverseConfiguration
//    Category toEntity(CategoryDto categoryDto);
    default CategoryDto toDto(Category category) {
        if (category == null) {
            return null;
        }
        CategoryDto.CategoryDtoBuilder<?, ?> categoryDto = CategoryDto.builder();
        categoryDto.category(toDto(category.getParentCategory()));
        categoryDto.id(category.getId());
        categoryDto.name(category.getName());
        categoryDto.createAt(category.getCreateAt());
        categoryDto.updateAt(category.getUpdateAt());

        return categoryDto.build();
    }

    default Category toEntity(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }
        Category.CategoryBuilder<?, ?> category = Category.builder();

        category.parentCategory(toEntity(categoryDto.getCategory()));
        category.id(categoryDto.getId());
        category.name(categoryDto.getName());
        category.createAt(categoryDto.getCreateAt());
        category.updateAt(categoryDto.getUpdateAt());

        return category.build();
    }

    List<Category> toEntities(List<CategoryDto> categoryDtos);

    List<CategoryDto> toDtos(List<Category> categorys);
}
