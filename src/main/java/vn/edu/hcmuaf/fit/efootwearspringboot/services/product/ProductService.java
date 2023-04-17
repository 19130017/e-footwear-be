package vn.edu.hcmuaf.fit.efootwearspringboot.services.product;

import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product.ProductCreateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product.ProductDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Product;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

import java.util.List;

public interface ProductService {
    public DataResult findProduct(Long id);

    public DataResult findProduct(String slug, Long color_id);

    public DataResult findProductsBySlug(String slug);

    public DataResult findProducts();

    public BaseResult deleteProduct(Long id);

    public BaseResult createProduct(ProductDto productDto);

    public BaseResult updateProduct(ProductDto productDto);

    public DataResult findProductsByCateSlug(String slug);
}
