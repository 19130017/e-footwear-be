package vn.edu.hcmuaf.fit.efootwearspringboot.services.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product.ProductDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.exception.NotFoundException;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.ProductMapper;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Category;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Product;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.CategoryRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.ProductRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    @Override
    public DataResult findProduct(Long id) {
        Optional<Product> optional = productRepository.findProductById(id);
        if (optional.isPresent()) {
            return DataResult.success(productMapper.toDto(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public DataResult findProduct(String slug, Long color_id) {
        Optional<Product> optional = productRepository.findProduct(slug, color_id);
        if (optional.isPresent()) {
            return DataResult.success(productMapper.toDto(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public DataResult findProductsBySlug(String slug) {
        Optional<List<Product>> optional = productRepository.findProductBySlug(slug);
        if (optional.isPresent()) {
            return DataResult.success(productMapper.toDtos(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public DataResult findProducts() {
        Optional<List<Product>> optional = productRepository.findProducts();
        if (optional.isPresent()) {
            return DataResult.success(productMapper.toDtos(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    public List<Product> findProductsByCategory(Category category) {
        if (category.getChildrenCategory() == null || category.getChildrenCategory().isEmpty()) {
            return productRepository.findProductByCateSlug(category.getSlug()).get();
        }
        List<Product> temps = new ArrayList<>();
        for (Category child : category.getChildrenCategory()) {
            temps.addAll(findProductsByCategory(child));
        }
        return temps;
    }

    @Override
    public DataResult findProductsByCateSlug(String slug) {
        Optional<Category> optional = categoryRepository.findCategoryBySlug(slug);

        if (optional.isPresent()) {
            List<Product> products = findProductsByCategory(optional.get());
            for (Product product : products) {
                System.out.println(product);
                String productSlug = product.getSlug();
                String temp = "";
                Integer count = 0;
                if (!productSlug.equals(temp)) {
                    temp = productSlug;
                    count = productRepository.countProductBySlug(temp);
                }
                product.setColorCounter(count);
                product.setSizeCounter(product.getDetails().size());
            }
            return DataResult.success(productMapper.toSlimDtos(products));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu danh mục với slug " + slug);
    }

    @Override
    public BaseResult deleteProduct(Long id) {
        return null;
    }

    @Override
    public BaseResult createProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public BaseResult updateProduct(ProductDto productDto) {
        return null;
    }
}
