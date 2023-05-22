package vn.edu.hcmuaf.fit.efootwearspringboot.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.category.CategoryCreateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.category.CategoryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.category.CategoryUpdateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.services.category.CategoryService;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponse;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseError;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseSuccess;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // get root category
    @GetMapping("/parent")
    public ResponseEntity<HttpResponse> findParentCategory() {
        DataResult dataResult = categoryService.findParentCategory();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @GetMapping()
    public ResponseEntity<HttpResponse> findCategories() {
        DataResult dataResult = categoryService.findCategories();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    // get category by id
    @GetMapping("/{id}")
    public ResponseEntity<HttpResponse> findCategoryById(@PathVariable("id") Long id) {
        DataResult dataResult = categoryService.findCategory(id);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    // get category by slug
    @GetMapping("/slug/{slug}")
    public ResponseEntity<HttpResponse> findCategoryBySlug(@PathVariable("slug") String slug) {
        DataResult dataResult = categoryService.findCategoryBySlug(slug);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    // create a new category
    @PostMapping
    public ResponseEntity<HttpResponse> createCategory(@RequestBody @Valid CategoryCreateDto categoryCreateDto) {
        CategoryDto categoryDto = CategoryDto.builder()
                .category(categoryCreateDto.getCategory())
                .name(categoryCreateDto.getName()).build();

        BaseResult baseResult = categoryService.createCategory(categoryDto);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success()) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }

    // update category
    @PutMapping("/{id}")
    public ResponseEntity<HttpResponse> updateCategory(@RequestBody @Valid CategoryUpdateDto categoryUpdateDto, @PathVariable("id") Long id) {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(id)
                .category(categoryUpdateDto.getCategory())
                .name(categoryUpdateDto.getName()).build();

        BaseResult baseResult = categoryService.updateCategory(categoryDto);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success()) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }


    // delete category
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpResponse> deleteCategory(@PathVariable("id") Long id) {
        BaseResult baseResult = categoryService.deleteCategory(id);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success()) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }

}
