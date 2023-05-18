package vn.edu.hcmuaf.fit.efootwearspringboot.services.product;

import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product.ProductCreateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product.ProductDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Product;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.ProductImage;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

import java.util.List;

public interface ProductService {
    DataResult findProduct(Long id);

    DataResult findProduct(String slug, Long color_id);

    DataResult findProductsBySlug(String slug);

    DataResult findProducts();

    DataResult findProducts(String query);

    BaseResult deleteProduct(Long id);

    BaseResult createProduct(ProductDto productDto);

    BaseResult updateProduct(ProductDto productDto);

    DataResult findProductsByCateSlug(String slug);

    DataResult findProductsHot();

    DataResult findProductsNew();
}
