package vn.edu.hcmuaf.fit.efootwearspringboot.services.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product.ProductDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.exception.NotFoundException;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.ProductMapper;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Product;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.product.ProductRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.EntityState;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public DataResult findProduct(Long id) {
        Optional<Product> optional = productRepository.findProductById(id);
        if (optional.isPresent()) {
            return DataResult.success(productMapper.toDto(optional.get()));
        } else {
            throw new NotFoundException("Không tìm thấy thông tin");
        }
    }

    @Override
    public DataResult findProducts() {
        List<Product> products = productRepository.findProducts();
        if (products.isEmpty())
            throw new NotFoundException("Không tìm thấy thông tin");
        return DataResult.success(productMapper.toDtos(products));
    }

    @Override
    public BaseResult deleteProduct(Long id) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            Product product = optional.get();
            product.setState(EntityState.DELETED);
            if (ObjectUtils.isEmpty(productRepository.save(product))) {
                return BaseResult.error(HttpStatus.BAD_REQUEST, "Category is not deleted");
            } else {
                return BaseResult.success();
            }
        } else {
            throw new NotFoundException("Không tìm thấy thông tin");
        }
    }
}
