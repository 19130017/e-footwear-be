package vn.edu.hcmuaf.fit.efootwearspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.QUERY;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Coupon;

import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    @Query(value = QUERY.COUPON.FIND_ALL, nativeQuery = true)
    Optional<List<Coupon>> findCoupons();

    @Query(value = QUERY.COUPON.FIND_COUPON, nativeQuery = true)
    Optional<Coupon> findCoupon(Long id);

    @Query(value = QUERY.COUPON.FIND_COUPON_BY_CODE, nativeQuery = true)
    Optional<Coupon> findCouponByCode(String code);
}
