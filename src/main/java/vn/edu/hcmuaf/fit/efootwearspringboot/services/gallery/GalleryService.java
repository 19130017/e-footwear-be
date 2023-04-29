package vn.edu.hcmuaf.fit.efootwearspringboot.services.gallery;

import vn.edu.hcmuaf.fit.efootwearspringboot.dto.gallery.GalleryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

public interface GalleryService {

    DataResult getGalleriesByType(String type_code);

    DataResult getCarousels();

    DataResult getCollections();

    DataResult getBanners();

    DataResult getAds();

    DataResult getFooters();

    DataResult findAll();

    BaseResult deleteGallery(Long id);

    BaseResult createGallery(GalleryDto galleryDto);

    BaseResult updateGallery(GalleryDto galleryDto);
}
