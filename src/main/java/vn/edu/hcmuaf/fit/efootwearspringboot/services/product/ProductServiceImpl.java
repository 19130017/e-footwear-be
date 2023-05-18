package vn.edu.hcmuaf.fit.efootwearspringboot.services.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.EntityState;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product.ProductCreateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product.ProductDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.exception.InternalServerException;
import vn.edu.hcmuaf.fit.efootwearspringboot.exception.NotFoundException;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.CategoryMapper;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.ColorMapper;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.ProductImageMapper;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.ProductMapper;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Category;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Product;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.ProductImage;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.TypeGallery;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.CategoryRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.ProductRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.TypeGalleryRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.MyParser;
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
    private ColorMapper colorMapper;
    private ProductImageMapper productImageMapper;
    private TypeGalleryRepository typeGalleryRepository;
    private CategoryMapper categoryMapper;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper, ColorMapper colorMapper, ProductImageMapper productImageMapper, TypeGalleryRepository typeGalleryRepository, CategoryMapper categoryMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
        this.colorMapper = colorMapper;
        this.productImageMapper = productImageMapper;
        this.typeGalleryRepository = typeGalleryRepository;
        this.categoryMapper = categoryMapper;
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

    @Override
    public DataResult findProducts(String query) {
        Optional<List<Product>> optional = productRepository.findProductsByName("%"+query+"%");
        if (optional.isEmpty()) throw new NotFoundException("Không tìm thấy dữ liệu");
        return DataResult.success(productMapper.toSlimDtos(optional.get()));
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
            setCounter(products);
            return DataResult.success(productMapper.toSlimDtos(products));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu danh mục với slug " + slug);
    }

    @Override
    public DataResult findProductsHot() {
        Optional<List<Product>> optional = productRepository.findProductsHot();
        if (optional.isPresent()) {
            List<Product> products = optional.get();
            setCounter(products);
            return DataResult.success(productMapper.toSlimDtos(products));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    public void setCounter(List<Product> products) {
        for (Product product : products) {
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
    }

    @Override
    public DataResult findProductsNew() {
        Optional<List<Product>> optional = productRepository.findProductsNew();
        if (optional.isPresent()) {
            List<Product> products = optional.get();
            setCounter(products);
            return DataResult.success(productMapper.toSlimDtos(products));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public BaseResult deleteProduct(Long id) {
        Optional<Product> optional = productRepository.findProductById(id);
        if (optional.isPresent()) {
            Product product = optional.get();
            product.setState(EntityState.DELETED);
            if (!ObjectUtils.isEmpty(productRepository.save(product))) {
                return BaseResult.success();
            }
        }
        throw new InternalServerException("Không xoá sản phẩm");
    }

    @Override
    public BaseResult createProduct(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        product.setState(EntityState.ACTIVE);
        product.setSlug(MyParser.convertToSlug(product.getName()));
        for (ProductImage productImage : product.getImages()) {
            String temp = "product";
            Optional<TypeGallery> optional = typeGalleryRepository.findTypeGalleryByType(temp);
            productImage.setTypeGallery(optional.get());
            productImage.setProduct(product);
            productImage.setState(EntityState.ACTIVE);
        }
        if (!ObjectUtils.isEmpty(productRepository.save(product))) {
            return BaseResult.success();
        }
        throw new InternalServerException("Không tạo mới sản phẩm");

    }

    @Override
    public BaseResult updateProduct(ProductDto productDto) {
        Optional<Product> optional = productRepository.findProductById(productDto.getId());
        if (optional.isPresent()) {
            Product product = optional.get();
            product.setName(productDto.getName());
            product.setDiscountRate(productDto.getDiscountRate());
            product.setOriginPrice(productDto.getOriginPrice());
            product.setDescription(productDto.getDescription());
            product.setCategory(categoryMapper.toEntity(productDto.getCategory()));
            product.setColor(colorMapper.toEntity(productDto.getColor()));
            for (int i = 0; i < product.getImages().size(); i++) {
                ProductImage productImage = product.getImages().get(i);
                productImage.setImageURL(productDto.getImages().get(i).getImageURL());
            }
            if (!ObjectUtils.isEmpty(productRepository.save(product))) {
                return BaseResult.success();
            }
            throw new InternalServerException("Không cập nhật dữ liệu của sản phẩm");
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }
}
