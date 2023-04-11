package vn.edu.hcmuaf.fit.efootwearspringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.efootwearspringboot.services.product.ProductService;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @GetMapping
//    @CrossOrigin(origins = "http://localhost:3000")
//    public ResponseEntity getProducts() {
//        DataResult dataResult = productService.findProduct();
//        return dataResult.getSuccess() ?
//                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
//                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
//    }
//
//
//    @GetMapping("/{id}")
//    public ResponseEntity getProduct(@Valid @PathVariable(name = "id") Long id) {
//        DataResult dataResult = productService.findProduct(id);
//        return dataResult.getSuccess() ?
//                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
//                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity deleteProduct(@Valid @PathVariable(name = "id") Long id) {
//        BaseResult baseResult = productService.deleteProduct(id);
//        return baseResult.getSuccess() ?
//                ResponseEntity.ok(HttpResponseSuccess.success()) :
//                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
//    }
//
//    @PostMapping()
//    public ResponseEntity createProduct(@Valid @RequestBody ProductCreateDto productCreateDto) {
//        ProductDto productDto = ProductDto.builder()
//                .name(productCreateDto.getName())
//                .stockQuantity(productCreateDto.getStockQuantity())
//                .importQuantity(productCreateDto.getImportQuantity())
//                .originPrice(productCreateDto.getOriginPrice())
//                .discountRate(productCreateDto.getDiscountRate())
//                .description(productCreateDto.getDescription())
//                .color(productCreateDto.getColor())
//                .brand(productCreateDto.getBrand())
//                .category(productCreateDto.getCategory())
//                .size(productCreateDto.getSize())
//                .build();
//        BaseResult baseResult = productService.createProduct(productDto);
//
//        return baseResult.getSuccess() ?
//                ResponseEntity.ok(HttpResponseSuccess.success()) :
//                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
//    }
//
//    @PutMapping()
//    public ResponseEntity updateProduct(@Valid @RequestBody ProductUpdateDto productUpdateDto) {
//        ProductDto productDto = ProductDto.builder()
//                .id(productUpdateDto.getId())
//                .name(productUpdateDto.getName())
//                .stockQuantity(productUpdateDto.getStockQuantity())
//                .importQuantity(productUpdateDto.getImportQuantity())
//                .originPrice(productUpdateDto.getOriginPrice())
//                .discountRate(productUpdateDto.getDiscountRate())
//                .description(productUpdateDto.getDescription())
//                .color(productUpdateDto.getColor())
//                .brand(productUpdateDto.getBrand())
//                .category(productUpdateDto.getCategory())
//                .size(productUpdateDto.getSize())
//                .build();
//        BaseResult baseResult = productService.updateProduct(productDto);
//        return baseResult.getSuccess() ?
//                ResponseEntity.ok(HttpResponseSuccess.success()) :
//                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
//    }

//    @GetMapping("/slug/{slug}")
//    public ResponseEntity getProductBySlug(@Valid @PathVariable("slug") String slug) {
//        DataResult dataResult = productService.findProductBySlug(slug);
//        return dataResult.getSuccess() ?
//                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
//                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
//    }

}
