package vn.edu.hcmuaf.fit.efootwearspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.QUERY;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.AddressDelivery;

import java.util.List;
import java.util.Optional;

public interface AddressDeliveryRepository extends JpaRepository<AddressDelivery, Long> {
    @Query(value = QUERY.ADDRESS_DELIVERY.FIND_ADDRESSES, nativeQuery = true)
    Optional<List<AddressDelivery>> findAddresses(Long accountId);

}
