package vn.edu.hcmuaf.fit.efootwearspringboot.services.gallery;

import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

public interface GalleryService {

    DataResult getGalleriesByType(String type_code);

    DataResult getCarousels();

    DataResult getCollections();

    DataResult getBanners();

    DataResult getAds();

    DataResult getFooters();
}
