package vn.edu.hcmuaf.fit.efootwearspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.QUERY;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query(value = QUERY.CUSTOMER.FIND_BY_ACCOUNT,nativeQuery = true)
    Optional<Customer> findByAccountId(Long accountId);
}
