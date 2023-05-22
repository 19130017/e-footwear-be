package vn.edu.hcmuaf.fit.efootwearspringboot.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.gallery.GalleryCreateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.gallery.GalleryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.gallery.GalleryUpdateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.services.gallery.GalleryService;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponse;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseError;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseSuccess;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

@RestController
@RequestMapping("/galleries")
public class GalleryController {
    private GalleryService galleryService;

    @Autowired
    public GalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @GetMapping("/type/{type_code}")
    public ResponseEntity<HttpResponse> getGalleriesByType(@PathVariable("type_code") String type_code) {
        DataResult dataResult = galleryService.getGalleriesByType(type_code);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @GetMapping("/slide")
    public ResponseEntity<HttpResponse> getCarousels() {
        DataResult dataResult = galleryService.getCarousels();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @GetMapping("/collection")
    public ResponseEntity<HttpResponse> getCollections() {
        DataResult dataResult = galleryService.getCollections();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @GetMapping("/banner")
    public ResponseEntity<HttpResponse> getBanners() {
        DataResult dataResult = galleryService.getBanners();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @GetMapping("/ads")
    public ResponseEntity<HttpResponse> getAds() {
        DataResult dataResult = galleryService.getAds();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @GetMapping("/footer")
    public ResponseEntity<HttpResponse> getFooters() {
        DataResult dataResult = galleryService.getFooters();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @GetMapping
    public ResponseEntity<HttpResponse> findAll() {
        DataResult dataResult = galleryService.findAll();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getHttpStatus(), dataResult.getMessage()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpResponse> deleteGallery(@PathVariable("id") Long id) {
        BaseResult baseResult = galleryService.deleteGallery(id);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(baseResult.getMessage())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));
    }

    @PostMapping
    public ResponseEntity<HttpResponse> createGallery(@RequestBody @Valid GalleryCreateDto galleryCreateDto) {
        GalleryDto galleryDto = GalleryDto.builder()
                .typeGallery(galleryCreateDto.getTypeGallery())
                .imageURL(galleryCreateDto.getImageURL())
                .link(galleryCreateDto.getLink())
                .title(galleryCreateDto.getTitle()).build();
        BaseResult baseResult = galleryService.createGallery(galleryDto);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(baseResult.getMessage())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));

    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpResponse> updateGallery(@RequestBody @Valid GalleryUpdateDto galleryUpdateDto, @PathVariable("id") Long id) {
        GalleryDto galleryDto = GalleryDto.builder()
                .id(id)
                .typeGallery(galleryUpdateDto.getTypeGallery())
                .imageURL(galleryUpdateDto.getImageURL())
                .link(galleryUpdateDto.getLink())
                .title(galleryUpdateDto.getTitle()).build();
        BaseResult baseResult = galleryService.updateGallery(galleryDto);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(baseResult.getMessage())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getHttpStatus(), baseResult.getMessage()));

    }
}

