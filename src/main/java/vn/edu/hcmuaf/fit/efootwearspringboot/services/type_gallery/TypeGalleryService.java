package vn.edu.hcmuaf.fit.efootwearspringboot.services.type_gallery;

import vn.edu.hcmuaf.fit.efootwearspringboot.dto.type_gallery.TypeGalleryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

public interface TypeGalleryService {
    DataResult findAll();

    DataResult findTypeGallery(Long id);

    BaseResult deleteTypeGallery(Long id);

    BaseResult createTypeGallery(TypeGalleryDto typeGalleryDto);

    BaseResult updateTypeGallery(TypeGalleryDto typeGalleryDto);
}
