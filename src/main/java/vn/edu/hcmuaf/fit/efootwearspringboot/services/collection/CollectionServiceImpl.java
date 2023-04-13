package vn.edu.hcmuaf.fit.efootwearspringboot.services.collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.collection.CollectionDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.exception.NotFoundException;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.CategoryMapper;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.CollectionMapper;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Collection;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.CollectionRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.EntityState;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

import java.util.List;
import java.util.Optional;

@Service
public class CollectionServiceImpl implements CollectionService {

    private CollectionRepository collectionRepository;
    private CollectionMapper collectionMapper;
    private CategoryMapper categoryMapper;

    @Autowired
    public CollectionServiceImpl(CollectionRepository collectionRepository, CollectionMapper collectionMapper, CategoryMapper categoryMapper) {
        this.collectionRepository = collectionRepository;
        this.collectionMapper = collectionMapper;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public DataResult findCollections() {
        Optional<List<Collection>> optional = collectionRepository.findCollections();
        if (optional.isPresent()) {
            return DataResult.success(collectionMapper.toDtos(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy collection");
    }

    @Override
    public DataResult findCollectionsByCate(String slug) {
        Optional<List<Collection>> optional = collectionRepository.findCollectionsByCate(slug);
        if (optional.isPresent()) {
            return DataResult.success(collectionMapper.toDtos(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy collection");
    }

    @Override
    public DataResult findCollection(Long id) {
        Optional<Collection> optional = collectionRepository.findCollection(id);
        if (optional.isPresent()) {
            return DataResult.success(collectionMapper.toDto(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy collection");
    }


    @Override
    public BaseResult createCollection(CollectionDto collectionDto) {
        Collection collection = collectionMapper.toEntity(collectionDto);
        collection.setState(EntityState.ACTIVE);
        if (!ObjectUtils.isEmpty(collectionRepository.save(collection))) {
            return BaseResult.success();
        }
        return BaseResult.error(HttpStatus.BAD_REQUEST, "Không tạo được collection.");
    }

    @Override
    public BaseResult updateCollection(CollectionDto collectionDto) {
        Optional<Collection> optional = collectionRepository.findCollection(collectionDto.getId());
        if (optional.isPresent()) {
            Collection collection = optional.get();
            collection.setName(collectionDto.getName());
            collection.setCategory(categoryMapper.toEntity(collectionDto.getCategory()));

            if (!ObjectUtils.isEmpty(collectionRepository.save(collection))) {
                return BaseResult.success();
            }
            return BaseResult.error(HttpStatus.BAD_REQUEST, "Không cập nhật được collection.");

        }
        throw new NotFoundException("Không tìm thấy collection.");
    }

    @Override
    public BaseResult deleteCollection(Long id) {
        Optional<Collection> optional = collectionRepository.findCollection(id);
        if (optional.isPresent()) {
            Collection collection = optional.get();
            collection.setState(EntityState.DELETED);
            if (!ObjectUtils.isEmpty(collectionRepository.save(collection))) {
                return BaseResult.success();
            }
            return BaseResult.error(HttpStatus.BAD_REQUEST, "Không xoá được collection.");

        }
        throw new NotFoundException("Không tìm thấy collection.");
    }

}
