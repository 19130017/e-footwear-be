package vn.edu.hcmuaf.fit.efootwearspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.QUERY;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = QUERY.PRODUCT.FIND_PRODUCTS, nativeQuery = true)
    Optional<List<Product>> findProducts();

    @Query(value = QUERY.PRODUCT.FIND_PRODUCT_BY_ID, nativeQuery = true)
    Optional<Product> findProductById(Long id);

    @Query(value = QUERY.PRODUCT.FIND_PRODUCT_BY_SLUG, nativeQuery = true)
    Optional<Product> findProductBySlug(String slug);

    @Query(value = QUERY.PRODUCT.FIND_PRODUCT_BY_CATE_SLUG,
            nativeQuery = true)
    Optional<List<Product>> findProductByCateSlug(String slug);

    @Query(countQuery = QUERY.PRODUCT.COUNT_PRODUCT_BY_SLUG)
    Integer countProductBySlug(String slug);
}
