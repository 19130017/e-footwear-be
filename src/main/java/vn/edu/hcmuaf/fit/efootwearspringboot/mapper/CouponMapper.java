package vn.edu.hcmuaf.fit.efootwearspringboot.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.coupon.CouponDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Coupon;

import java.util.List;

@Mapper(componentModel = "spring")
@Component("couponMapper")
public interface CouponMapper {
    CouponMapper INSTANCE = Mappers.getMapper(CouponMapper.class);

    CouponDto toDto(Coupon coupon);

    Coupon toEntity(CouponDto couponDto);

    List<Coupon> toEntities(List<CouponDto> couponDtos);

    List<CouponDto> toDtos(List<Coupon> coupons);

}
