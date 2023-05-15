package vn.edu.hcmuaf.fit.efootwearspringboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.detail.DetailDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.detail.DetailSlimDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Detail;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SizeMapper.class, ProductMapper.class})
@Component("detailMapper")
public interface DetailMapper {
    DetailMapper INSTANCE = Mappers.getMapper(DetailMapper.class);

    DetailDto toDto(Detail detail);

    DetailSlimDto toSlimDto(Detail detail);

    Detail toEntity(DetailDto detailDto);

    List<Detail> toEntities(List<DetailDto> detailDtos);

    List<DetailDto> toDtos(List<Detail> details);
}
