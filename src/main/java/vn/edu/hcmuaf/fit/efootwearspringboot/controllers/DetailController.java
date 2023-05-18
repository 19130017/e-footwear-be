package vn.edu.hcmuaf.fit.efootwearspringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.detail.DetailCreateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.detail.DetailDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.detail.DetailSlimDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.detail.DetailUpdateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.services.detail.DetailService;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponse;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseError;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseSuccess;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

@RestController
@RequestMapping("/api/v1/details")
public class DetailController {

    private DetailService detailService;

    @Autowired
    public DetailController(DetailService detailService) {
        this.detailService = detailService;
    }

    @GetMapping("{size_id}/product/{slug}/{colorId}")
    public ResponseEntity getDetail(
            @PathVariable("size_id") Long size_id,
            @PathVariable("slug") String slug,
            @PathVariable("colorId") Long colorId) {
        DataResult dataResult = detailService.findDetailByProduct(size_id, slug, colorId);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
    }

    @GetMapping()
    public ResponseEntity<HttpResponse> findAll() {
        DataResult dataResult = detailService.findAll();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
    }

    @PostMapping
    public ResponseEntity<HttpResponse> createDetail(@RequestBody DetailCreateDto detailCreateDto) {
        DetailDto detailDto = DetailDto.builder()
                .stockQuantity(detailCreateDto.getStockQuantity())
                .size(detailCreateDto.getSize())
                .product(detailCreateDto.getProduct())
                .build();
        BaseResult baseResult = detailService.createDetail(detailDto);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(baseResult.getMessage())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getMessage()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpResponse> deleteDetail(@PathVariable("id") Long id) {
        BaseResult baseResult = detailService.deleteDetail(id);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(baseResult.getMessage())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getMessage()));
    }

    @PutMapping("{id}")
    public ResponseEntity<HttpResponse> updateDetail(@RequestBody DetailUpdateDto detailUpdateDto, @PathVariable("id") Long id) {
        DetailDto detailDto = DetailDto.builder()
                .id(id)
                .stockQuantity(detailUpdateDto.getStockQuantity())
                .size(detailUpdateDto.getSize())
                .product(detailUpdateDto.getProduct())
                .build();
        BaseResult baseResult = detailService.updateDetail(detailDto);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(baseResult.getMessage())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getMessage()));
    }
}
