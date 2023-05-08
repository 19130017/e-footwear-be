package vn.edu.hcmuaf.fit.efootwearspringboot.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.coupon.CouponCreateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.coupon.CouponDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.coupon.CouponUpdateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.services.coupon.CouponService;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseError;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseSuccess;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

import java.text.ParseException;

@RestController
@RequestMapping("/api/v1/coupons")
public class CouponController {
    CouponService couponService;

    @Autowired
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping
    public ResponseEntity findCoupons() {
        DataResult dataResult = couponService.findAll();
        return dataResult.getSuccess() ? ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) : ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
    }

    @GetMapping("/{id}")
    public ResponseEntity findCoupon(@PathVariable("id") Long id) {
        DataResult dataResult = couponService.findCoupon(id);
        return dataResult.getSuccess() ? ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) : ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
    }

    @PostMapping
    public ResponseEntity createCoupon(@RequestBody @Valid CouponCreateDto couponCreateDto) throws ParseException {
        CouponDto couponDto = CouponDto.builder()
                .code(couponCreateDto.getCode())
                .maxUsage(couponCreateDto.getMaxUsage())
                .price(couponCreateDto.getPrice())
                .endTime(couponCreateDto.getEndTime())
                .build();
        BaseResult baseResult = couponService.createCoupon(couponDto);
        return baseResult.getSuccess() ? ResponseEntity.ok(HttpResponseSuccess.success()) : ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getMessage()));

    }

    @PutMapping("/{id}")
    public ResponseEntity updateCoupon(@RequestBody @Valid CouponUpdateDto couponUpdateDto, @PathVariable("id") Long id) {
        CouponDto couponDto = CouponDto.builder().id(id).code(couponUpdateDto.getCode()).maxUsage(couponUpdateDto.getMaxUsage()).price(couponUpdateDto.getPrice()).endTime(couponUpdateDto.getEndTime()).build();
        BaseResult baseResult = couponService.updateCoupon(couponDto);
        return baseResult.getSuccess() ? ResponseEntity.ok(HttpResponseSuccess.success()) : ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getMessage()));

    }
}
