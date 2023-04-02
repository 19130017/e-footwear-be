package vn.edu.hcmuaf.fit.efootwearspringboot.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Product;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.ProductRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.services.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProduct(Long id) {
        return null;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
