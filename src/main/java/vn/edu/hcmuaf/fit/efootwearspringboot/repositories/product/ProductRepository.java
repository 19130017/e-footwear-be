package vn.edu.hcmuaf.fit.efootwearspringboot.repositories.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select * from products where state = 'ACTIVE'", nativeQuery = true)
    List<Product> findProducts();

    @Query(value = "select * from products where state = 'ACTIVE' and id = ? ", nativeQuery = true)
    Optional<Product> findProductById(Long id);

    @Query(value = "select * from products where state = 'ACTIVE' and slug = ? ", nativeQuery = true)
    Optional<Product> findProductBySlug(String slug);


}
