package vn.edu.hcmuaf.fit.efootwearspringboot.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product.ProductCreateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product.ProductDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product.ProductUpdateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.services.product.ProductService;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseError;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseSuccess;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity getProducts() {
        DataResult dataResult = productService.findProducts();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
    }

    @GetMapping("/category/slug/{slug}")
    public ResponseEntity getProductsByCateSlug(@PathVariable("slug") String slug) {
        DataResult dataResult = productService.findProductsByCateSlug(slug);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
    }


    // get slug & color
    @GetMapping("/slug/{slug}/color/{id}")
    public ResponseEntity getProduct(@PathVariable("slug") String slug, @PathVariable("id") Long color_id) {
        DataResult dataResult = productService.findProduct(slug, color_id);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity getProductsBySlug(@Valid @PathVariable("slug") String slug) {
        DataResult dataResult = productService.findProductsBySlug(slug);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
    }

    @GetMapping("/{id}")
    public ResponseEntity getProduct(@PathVariable(name = "id") Long id) {
        DataResult dataResult = productService.findProduct(id);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@Valid @PathVariable(name = "id") Long id) {
        BaseResult baseResult = productService.deleteProduct(id);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success()) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }

    @PostMapping()
    public ResponseEntity createProduct(@Valid @RequestBody ProductCreateDto productCreateDto) {
        ProductDto productDto = ProductDto.builder()
                .name(productCreateDto.getName())
                .originPrice(productCreateDto.getOriginPrice())
                .discountRate(productCreateDto.getDiscountRate())
                .description(productCreateDto.getDescription())
                .color(productCreateDto.getColor())
                .category(productCreateDto.getCategory())
                .build();
        BaseResult baseResult = productService.createProduct(productDto);

        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success()) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }

    @PutMapping()
    public ResponseEntity updateProduct(@Valid @RequestBody ProductUpdateDto productUpdateDto) {
        ProductDto productDto = ProductDto.builder()
                .id(productUpdateDto.getId())
                .name(productUpdateDto.getName())
                .originPrice(productUpdateDto.getOriginPrice())
                .discountRate(productUpdateDto.getDiscountRate())
                .description(productUpdateDto.getDescription())
                .color(productUpdateDto.getColor())
                .category(productUpdateDto.getCategory())
                .build();
        BaseResult baseResult = productService.updateProduct(productDto);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success()) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }

    @GetMapping("/hot")
    public ResponseEntity getProductsHot() {
        DataResult dataResult = productService.findProductsHot();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }
    @GetMapping("/new")
    public ResponseEntity getProductsNew() {
        DataResult dataResult = productService.findProductsNew();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

}
