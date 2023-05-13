package vn.edu.hcmuaf.fit.efootwearspringboot.services.gallery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.EntityState;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.gallery.GalleryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.exception.NotFoundException;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.GalleryMapper;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.TypeGalleryMapper;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Gallery;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.GalleryRepository;

import vn.edu.hcmuaf.fit.efootwearspringboot.constants.EntityState;

import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

import java.util.List;
import java.util.Optional;

@Service
public class GalleryServiceImpl implements GalleryService {
    private GalleryRepository galleryRepository;
    private GalleryMapper galleryMapper;
    private TypeGalleryMapper typeGalleryMapper;

    @Autowired
    public GalleryServiceImpl(GalleryRepository galleryRepository, GalleryMapper galleryMapper, TypeGalleryMapper typeGalleryMapper) {
        this.galleryRepository = galleryRepository;
        this.galleryMapper = galleryMapper;
        this.typeGalleryMapper = typeGalleryMapper;
    }

    @Override
    public DataResult getGalleriesByType(String type_code) {
        Optional<List<Gallery>> optional = galleryRepository.findGalleriesByType(type_code);
        if (optional.isPresent()) {
            return DataResult.success(galleryMapper.toDtos(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public DataResult getCarousels() {
        Optional<List<Gallery>> optional = galleryRepository.findCarousels();
        if (optional.isPresent()) {
            return DataResult.success(galleryMapper.toDtos(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public DataResult getCollections() {
        Optional<List<Gallery>> optional = galleryRepository.findCollections();
        if (optional.isPresent()) {
            return DataResult.success(galleryMapper.toDtos(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public DataResult getBanners() {
        Optional<List<Gallery>> optional = galleryRepository.findBanners();
        if (optional.isPresent()) {
            return DataResult.success(galleryMapper.toDtos(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public DataResult getAds() {
        Optional<List<Gallery>> optional = galleryRepository.findAds();
        if (optional.isPresent()) {
            return DataResult.success(galleryMapper.toDtos(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public DataResult getFooters() {
        Optional<List<Gallery>> optional = galleryRepository.findFooters();
        if (optional.isPresent()) {
            return DataResult.success(galleryMapper.toDtos(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public DataResult findAll() {
        Optional<List<Gallery>> optional = galleryRepository.findGalleries();
        if (optional.isPresent()) {
            return DataResult.success(galleryMapper.toDtos(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");

    }

    @Override
    public BaseResult deleteGallery(Long id) {
        Optional<Gallery> optional = galleryRepository.findById(id);
        if (optional.isPresent()) {
            Gallery gallery = optional.get();
            gallery.setState(EntityState.DELETED);
            if (!ObjectUtils.isEmpty(galleryRepository.save(gallery))) {
                return BaseResult.success();
            }
            return BaseResult.error(HttpStatus.BAD_REQUEST, "Không thể xoá dữ liệu.");
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public BaseResult createGallery(GalleryDto galleryDto) {
        Gallery gallery = galleryMapper.toEntity(galleryDto);
        gallery.setState(EntityState.ACTIVE);
        if (!ObjectUtils.isEmpty(galleryRepository.save(gallery))) {
            return BaseResult.success();
        }
        return BaseResult.error(HttpStatus.BAD_REQUEST, "Không thể thêm dữ liệu.");
    }

    @Override
    public BaseResult updateGallery(GalleryDto galleryDto) {
        Optional<Gallery> optional = galleryRepository.findById(galleryDto.getId());
        if (optional.isPresent()) {
            Gallery gallery = optional.get();
            gallery.setImageURL(galleryDto.getImageURL());
            gallery.setLink(galleryDto.getLink());
            gallery.setTitle(galleryDto.getTitle());
            gallery.setTypeGallery(typeGalleryMapper.toEntity(galleryDto.getTypeGallery()));
            if (!ObjectUtils.isEmpty(galleryRepository.save(gallery))) {
                return BaseResult.success();
            }
            return BaseResult.error(HttpStatus.BAD_REQUEST, "Không thể cập nhật dữ liệu.");
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");

    }
}
