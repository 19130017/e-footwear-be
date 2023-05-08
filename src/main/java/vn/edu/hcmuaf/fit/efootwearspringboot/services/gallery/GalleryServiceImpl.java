package vn.edu.hcmuaf.fit.efootwearspringboot.services.gallery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.efootwearspringboot.exception.NotFoundException;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.GalleryMapper;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Gallery;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.GalleryRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

import java.util.List;
import java.util.Optional;

@Service
public class GalleryServiceImpl implements GalleryService {
    private GalleryRepository galleryRepository;
    private GalleryMapper galleryMapper;

    @Autowired
    public GalleryServiceImpl(GalleryRepository galleryRepository, GalleryMapper galleryMapper) {
        this.galleryRepository = galleryRepository;
        this.galleryMapper = galleryMapper;
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
}
