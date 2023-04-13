package vn.edu.hcmuaf.fit.efootwearspringboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.category.CategoryCreateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.category.CategoryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.collection.CollectionCreateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.collection.CollectionDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.services.collection.CollectionService;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseError;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.response.HttpResponseSuccess;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

@RestController
@RequestMapping("/api/v1/collections")
public class CollectionController {
    private CollectionService collectionService;

    @Autowired
    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    // get all collections
    @GetMapping
    public ResponseEntity findCollections() {
        DataResult dataResult = collectionService.findCollections();
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
    }

    // get all collections by category slug
    @GetMapping("/slug/{slug}")
    public ResponseEntity findCollectionsByCate(@PathVariable("slug") String slug) {
        DataResult dataResult = collectionService.findCollectionsByCate(slug);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
    }

    // get one collection
    @GetMapping("/{id}")
    public ResponseEntity findCollection(@PathVariable("id") Long id) {
        DataResult dataResult = collectionService.findCollection(id);
        return dataResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success(dataResult.getData())) :
                ResponseEntity.badRequest().body(HttpResponseError.error(dataResult.getMessage()));
    }

    // create a new collection
    @PostMapping()
    public ResponseEntity createCollection(@RequestBody CollectionCreateDto collectionCreateDto) {
        CollectionDto collectionDto = CollectionDto.builder()
                .name(collectionCreateDto.getName())
                .category(collectionCreateDto.getCategory()).build();
        BaseResult baseResult = collectionService.createCollection(collectionDto);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success()) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getMessage()));
    }

    // update a collection
    @PutMapping("/{id}")
    public ResponseEntity updateCollection(@RequestBody CollectionCreateDto collectionCreateDto, @PathVariable("id") Long id) {
        CollectionDto collectionDto = CollectionDto.builder()
                .id(id)
                .name(collectionCreateDto.getName())
                .category(collectionCreateDto.getCategory()).build();
        BaseResult baseResult = collectionService.updateCollection(collectionDto);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success()) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getMessage()));
    }

    // delete a collection
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCollection(@PathVariable("id") Long id) {
        BaseResult baseResult = collectionService.deleteCollection(id);
        return baseResult.getSuccess() ?
                ResponseEntity.ok(HttpResponseSuccess.success()) :
                ResponseEntity.badRequest().body(HttpResponseError.error(baseResult.getMessage()));
    }
}
