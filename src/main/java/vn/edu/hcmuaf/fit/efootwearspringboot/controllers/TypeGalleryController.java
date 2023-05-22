package vn.edu.hcmuaf.fit.efootwearspringboot.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.type_gallery.TypeGalleryCreateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.type_gallery.TypeGalleryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.type_gallery.TypeGalleryUpdateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.services.type_gallery.TypeGalleryService;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponse;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseError;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseSuccess;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

@RestController
@RequestMapping("/type-galleries")
public class TypeGalleryController {
    private TypeGalleryService typeGalleryService;

    @Autowired
    public TypeGalleryController(TypeGalleryService typeGalleryService) {
        this.typeGalleryService = typeGalleryService;
    }

    @GetMapping
    public ResponseEntity<HttpResponse> findTypeGalleries() {
        DataResult dataResult = typeGalleryService.findAll();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HttpResponse> findTypeGallery(@PathVariable("id") Long id) {
        DataResult dataResult = typeGalleryService.findTypeGallery(id);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @PostMapping
    public ResponseEntity<HttpResponse> createTypeGallery(@RequestBody @Valid TypeGalleryCreateDto typeGalleryCreateDto) {
        TypeGalleryDto typeGalleryDto = TypeGalleryDto.builder()
                .typeCode(typeGalleryCreateDto.getTypeCode())
                .typeName(typeGalleryCreateDto.getTypeName())
                .build();
        BaseResult baseResult = typeGalleryService.createTypeGallery(typeGalleryDto);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success()) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpResponse> updateTypeGallery(@RequestBody @Valid TypeGalleryUpdateDto typeGalleryUpdateDto, @PathVariable("id") Long id) {
        TypeGalleryDto typeGalleryDto = TypeGalleryDto.builder()
                .id(id)
                .typeCode(typeGalleryUpdateDto.getTypeCode())
                .typeName(typeGalleryUpdateDto.getTypeName())
                .build();
        BaseResult baseResult = typeGalleryService.updateTypeGallery(typeGalleryDto);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success()) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpResponse> deleteTypeGallery(@PathVariable("id") Long id) {
        BaseResult baseResult = typeGalleryService.deleteTypeGallery(id);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success()) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }
}
