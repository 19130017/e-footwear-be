package vn.edu.hcmuaf.fit.efootwearspringboot.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.collection.CollectionDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Collection;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
@Component("collectionMapper")
public interface CollectionMapper {
    CollectionDto toDto(Collection collection);

    Collection toEntity(CollectionDto collectionDto);

    List<Collection> toEntities(List<CollectionDto> collectionDtos);

    List<CollectionDto> toDtos(List<Collection> collections);
}
