package vn.edu.hcmuaf.fit.efootwearspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
