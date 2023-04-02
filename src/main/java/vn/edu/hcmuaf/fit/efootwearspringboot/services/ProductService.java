package vn.edu.hcmuaf.fit.efootwearspringboot.services;

import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Product;

import java.util.List;

public interface ProductService {
    public Product getProduct(Long id);

    public List<Product> getProducts();
}
