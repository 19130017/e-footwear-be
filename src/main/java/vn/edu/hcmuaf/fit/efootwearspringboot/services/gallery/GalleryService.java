package vn.edu.hcmuaf.fit.efootwearspringboot.services.gallery;

import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

public interface GalleryService {

    DataResult getGalleriesByType(String type_code);
}
