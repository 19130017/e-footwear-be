package vn.edu.hcmuaf.fit.efootwearspringboot.services.coupon;

import vn.edu.hcmuaf.fit.efootwearspringboot.dto.coupon.CouponDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;


public interface CouponService {
    DataResult findAll();

    DataResult findCoupon(Long id);

    BaseResult createCoupon(CouponDto couponDto);

    BaseResult updateCoupon(CouponDto couponDto);

    DataResult findCouponByCode(String code);
}
