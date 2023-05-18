package vn.edu.hcmuaf.fit.efootwearspringboot.services.type_gallery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.EntityState;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.type_gallery.TypeGalleryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.exception.InternalServerException;
import vn.edu.hcmuaf.fit.efootwearspringboot.exception.NotFoundException;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.TypeGalleryMapper;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.TypeGallery;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.TypeGalleryRepository;

import vn.edu.hcmuaf.fit.efootwearspringboot.constants.EntityState;


import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Service
public class TypeGalleryServiceImpl implements TypeGalleryService {
    private TypeGalleryRepository typeGalleryRepository;
    private TypeGalleryMapper typeGalleryMapper;

    @Autowired
    public TypeGalleryServiceImpl(TypeGalleryRepository typeGalleryRepository, TypeGalleryMapper typeGalleryMapper) {
        this.typeGalleryRepository = typeGalleryRepository;
        this.typeGalleryMapper = typeGalleryMapper;
    }

    @Override

    public DataResult findAll() {
        Optional<List<TypeGallery>> optional = typeGalleryRepository.findTypeGalleries();
        if (optional.isPresent()) {
            return DataResult.success(typeGalleryMapper.toDtos(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public DataResult findTypeGallery(Long id) {
        Optional<TypeGallery> optional = typeGalleryRepository.findById(id);
        if (optional.isPresent()) {
            return DataResult.success(typeGalleryMapper.toDto(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public BaseResult deleteTypeGallery(Long id) {
        Optional<TypeGallery> optional = typeGalleryRepository.findById(id);
        if (optional.isPresent()) {
            TypeGallery typeGallery = optional.get();
            typeGallery.setState(EntityState.DELETED);
            if (!ObjectUtils.isEmpty(typeGalleryRepository.save(typeGallery))) {
                return BaseResult.success();
            }
            throw new InternalServerException("Không xoá type gallery");
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public BaseResult createTypeGallery(TypeGalleryDto typeGalleryDto) {
        TypeGallery typeGallery = typeGalleryMapper.toEntity(typeGalleryDto);
        typeGallery.setState(EntityState.ACTIVE);
        if (!ObjectUtils.isEmpty(typeGalleryRepository.save(typeGallery))) {
            return BaseResult.success();
        }
        throw new InternalServerException("Không thêm mới type gallery");
    }

    @Override
    public BaseResult updateTypeGallery(TypeGalleryDto typeGalleryDto) {
        Optional<TypeGallery> optional = typeGalleryRepository.findById(typeGalleryDto.getId());
        if (optional.isPresent()) {
            TypeGallery typeGallery = optional.get();
            typeGallery.setTypeCode(typeGalleryDto.getTypeCode());
            typeGallery.setTypeName(typeGalleryDto.getTypeName());
            if (!ObjectUtils.isEmpty(typeGalleryRepository.save(typeGallery))) {
                return BaseResult.success();
            }
            throw new InternalServerException("Không cập nhật type gallery");
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }
}
