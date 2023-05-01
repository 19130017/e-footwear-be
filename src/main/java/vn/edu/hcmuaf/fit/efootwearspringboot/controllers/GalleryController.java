package vn.edu.hcmuaf.fit.efootwearspringboot.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.gallery.GalleryCreateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.gallery.GalleryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.gallery.GalleryUpdateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.services.gallery.GalleryService;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseError;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseSuccess;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

@RestController
@RequestMapping("/api/v1/galleries")
public class GalleryController {
    private GalleryService galleryService;

    @Autowired
    public GalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @GetMapping("/type/{type_code}")
    public ResponseEntity getGalleriesByType(@PathVariable("type_code") String type_code) {
        DataResult dataResult = galleryService.getGalleriesByType(type_code);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
    }

    @GetMapping("/slide")
    public ResponseEntity getCarousels() {
        DataResult dataResult = galleryService.getCarousels();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
    }

    @GetMapping("/collection")
    public ResponseEntity getCollections() {
        DataResult dataResult = galleryService.getCollections();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
    }

    @GetMapping("/banner")
    public ResponseEntity getBanners() {
        DataResult dataResult = galleryService.getBanners();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
    }

    @GetMapping("/ads")
    public ResponseEntity getAds() {
        DataResult dataResult = galleryService.getAds();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
    }

    @GetMapping("/footer")
    public ResponseEntity getFooters() {
        DataResult dataResult = galleryService.getFooters();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
    }

    @GetMapping
    public ResponseEntity findAll() {
        DataResult dataResult = galleryService.findAll();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGallery(@PathVariable("id") Long id) {
        BaseResult baseResult = galleryService.deleteGallery(id);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(baseResult.getMessage())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getMessage()));
    }

    @PostMapping
    public ResponseEntity createGallery(@RequestBody @Valid GalleryCreateDto galleryCreateDto) {
        GalleryDto galleryDto = GalleryDto.builder()
                .typeGallery(galleryCreateDto.getTypeGallery())
                .imageURL(galleryCreateDto.getImageURL())
                .link(galleryCreateDto.getLink())
                .title(galleryCreateDto.getTitle()).build();
        BaseResult baseResult = galleryService.createGallery(galleryDto);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(baseResult.getMessage())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getMessage()));

    }

    @PutMapping("/{id}")
    public ResponseEntity updateGallery(@RequestBody @Valid GalleryUpdateDto galleryUpdateDto, @PathVariable("id") Long id) {
        GalleryDto galleryDto = GalleryDto.builder()
                .id(id)
                .typeGallery(galleryUpdateDto.getTypeGallery())
                .imageURL(galleryUpdateDto.getImageURL())
                .link(galleryUpdateDto.getLink())
                .title(galleryUpdateDto.getTitle()).build();
        BaseResult baseResult = galleryService.updateGallery(galleryDto);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(baseResult.getMessage())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getMessage()));

    }
}

