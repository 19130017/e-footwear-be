package vn.edu.hcmuaf.fit.efootwearspringboot.services.collection;

import vn.edu.hcmuaf.fit.efootwearspringboot.dto.collection.CollectionDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

public interface CollectionService {
    public DataResult findCollection(Long id);

    public BaseResult deleteCollection(Long id);

    public BaseResult createCollection(CollectionDto CollectionDto);

    public BaseResult updateCollection(CollectionDto CollectionDto);

    public DataResult findCollections();

    DataResult findCollectionsByCate(String slug);
}
