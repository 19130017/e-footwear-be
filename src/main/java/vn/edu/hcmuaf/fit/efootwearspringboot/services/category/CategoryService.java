package vn.edu.hcmuaf.fit.efootwearspringboot.services.category;

import vn.edu.hcmuaf.fit.efootwearspringboot.dto.category.CategoryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

public interface CategoryService {
    public DataResult findCategory(Long id);

    public DataResult findCategoryBySlug(String slug);


    public BaseResult deleteCategory(Long id);

    public BaseResult createCategory(CategoryDto CategoryDto);

    public BaseResult updateCategory(CategoryDto CategoryDto);

    public DataResult findParentCategory();

    public DataResult findChildrenCategory(Long id);

    public DataResult findCategories();
}
