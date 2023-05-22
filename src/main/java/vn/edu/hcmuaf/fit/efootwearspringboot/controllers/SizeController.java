package vn.edu.hcmuaf.fit.efootwearspringboot.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.size.SizeCreateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.size.SizeDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.size.SizeUpdateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.services.size.SizeService;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponse;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseError;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseSuccess;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

@RestController()
@RequestMapping("/sizes")
public class SizeController {
    private SizeService sizeService;

    @Autowired
    public SizeController(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @GetMapping
    public ResponseEntity<HttpResponse> findSizes() {
        DataResult dataResult = sizeService.findAll();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HttpResponse> findSize(@PathVariable("id") Long id) {
        DataResult dataResult = sizeService.findSize(id);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @PostMapping
    public ResponseEntity<HttpResponse> createSize(@RequestBody @Valid SizeCreateDto sizeCreateDto) {
        SizeDto sizeDto = SizeDto.builder()
                .value(sizeCreateDto.getValue())
                .build();

        BaseResult baseResult = sizeService.createSize(sizeDto);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success()) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpResponse> updateSize(@RequestBody @Valid  SizeUpdateDto sizeUpdateDto, @PathVariable("id") Long id) {
        SizeDto sizeDto = SizeDto.builder()
                .id(id)
                .value(sizeUpdateDto.getValue())
                .build();

        BaseResult baseResult = sizeService.updateSize(sizeDto);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success()) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpResponse> deleteSize(@PathVariable("id") Long id) {
        BaseResult baseResult = sizeService.deleteSize(id);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success()) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }
}
