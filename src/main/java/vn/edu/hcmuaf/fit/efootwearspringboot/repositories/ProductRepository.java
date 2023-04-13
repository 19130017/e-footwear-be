package vn.edu.hcmuaf.fit.efootwearspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.QUERY;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = QUERY.PRODUCT.FIND_PRODUCTS, nativeQuery = true)
    List<Product> findProducts();

    @Query(value = QUERY.PRODUCT.FIND_PRODUCT_BY_ID, nativeQuery = true)
    Optional<Product> findProductById(Long id);

    @Query(value = QUERY.PRODUCT.FIND_PRODUCT_BY_SLUG, nativeQuery = true)
    Optional<Product> findProductBySlug(String slug);

}
