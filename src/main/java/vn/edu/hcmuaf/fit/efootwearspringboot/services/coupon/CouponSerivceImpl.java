package vn.edu.hcmuaf.fit.efootwearspringboot.services.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.coupon.CouponDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.exception.NotFoundException;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.CouponMapper;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Coupon;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.CouponRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class CouponSerivceImpl implements CouponService {
    private CouponRepository couponRepository;
    private CouponMapper couponMapper;

    @Autowired
    public CouponSerivceImpl(CouponRepository couponRepository, CouponMapper couponMapper) {
        this.couponRepository = couponRepository;
        this.couponMapper = couponMapper;
    }

    @Override
    public DataResult findAll() {
        Optional<List<Coupon>> optional = couponRepository.findCoupons();
        if (optional.isPresent()) {
            return DataResult.success(couponMapper.toDtos(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public DataResult findCoupon(Long id) {
        Optional<Coupon> optional = couponRepository.findById(id);
        if (optional.isPresent()) {
            return DataResult.success(couponMapper.toDto(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public BaseResult createCoupon(CouponDto couponDto) {
        Coupon coupon = couponMapper.toEntity(couponDto);
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        if (!ObjectUtils.isEmpty(couponRepository.save(coupon))) {
            return BaseResult.success();
        }
        return BaseResult.error(HttpStatus.BAD_REQUEST, "Không thể thêm dữ liệu.");

    }

    @Override
    public BaseResult updateCoupon(CouponDto couponDto) {
        Optional<Coupon> optional = couponRepository.findById(couponDto.getId());
        if (optional.isPresent()) {
            Coupon coupon = optional.get();
            coupon.setCode(couponDto.getCode());
            coupon.setMaxUsage(couponDto.getMaxUsage());
            coupon.setPrice(couponDto.getPrice());
            coupon.setEndTime(couponDto.getEndTime());
            if (!ObjectUtils.isEmpty(couponRepository.save(coupon))) {
                return BaseResult.success();
            }
            return BaseResult.error(HttpStatus.BAD_REQUEST, "Không thể cập nhật dữ liệu.");
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public DataResult findCouponByCode(String code) {
        Optional<Coupon> optional = couponRepository.findCouponByCode(code);
        if (optional.isPresent()) {
            return DataResult.success(couponMapper.toDto(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }
}
