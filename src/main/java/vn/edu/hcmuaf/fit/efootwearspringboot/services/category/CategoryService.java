package vn.edu.hcmuaf.fit.efootwearspringboot.services.category;

import vn.edu.hcmuaf.fit.efootwearspringboot.dto.category.CategoryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

 interface CategoryService {
     DataResult findCategory(Long id);

     DataResult findCategoryBySlug(String slug);


     BaseResult deleteCategory(Long id);

     BaseResult createCategory(CategoryDto CategoryDto);

     BaseResult updateCategory(CategoryDto CategoryDto);

     DataResult findParentCategory();

     DataResult findCategories();
}
