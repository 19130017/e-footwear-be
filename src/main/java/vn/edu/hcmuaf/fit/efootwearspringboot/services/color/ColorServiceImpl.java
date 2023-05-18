package vn.edu.hcmuaf.fit.efootwearspringboot.services.color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.color.ColorDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.exception.InternalServerException;
import vn.edu.hcmuaf.fit.efootwearspringboot.exception.NotFoundException;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.ColorMapper;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Color;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.ColorRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.EntityState;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

import java.util.List;
import java.util.Optional;

@Service
public class ColorServiceImpl implements ColorService {
    private ColorRepository colorRepository;
    private ColorMapper colorMapper;

    @Autowired
    public ColorServiceImpl(ColorRepository colorRepository, ColorMapper colorMapper) {
        this.colorRepository = colorRepository;
        this.colorMapper = colorMapper;
    }

    @Override
    public DataResult findAll() {
        Optional<List<Color>> optional = colorRepository.findColors();
        if (optional.isPresent()) {
            return DataResult.success(colorMapper.toDtos(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public DataResult findColor(Long id) {
        Optional<Color> optional = colorRepository.findById(id);
        if (optional.isPresent()) {
            return DataResult.success(colorMapper.toDto(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public BaseResult deleteColor(Long id) {
        Optional<Color> optional = colorRepository.findById(id);
        if (optional.isPresent()) {
            Color color = optional.get();
            color.setState(EntityState.DELETED);
            if (!ObjectUtils.isEmpty(colorRepository.save(color))) {
                return BaseResult.success();
            }
            throw new InternalServerException("Không thể xoá màu.");
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public BaseResult createColor(ColorDto colorDto) {
        Color color = colorMapper.toEntity(colorDto);
        color.setState(EntityState.ACTIVE);
        if (!ObjectUtils.isEmpty(colorRepository.save(color))) {
            return BaseResult.success();
        }
        throw new InternalServerException("Không thể thêm mới màu.");
    }

    @Override
    public BaseResult updateColor(ColorDto colorDto) {
        Optional<Color> optional = colorRepository.findById(colorDto.getId());
        if (optional.isPresent()) {
            Color color = optional.get();
            color.setCodeColor(colorDto.getCodeColor());
            color.setName(colorDto.getName());
            if (!ObjectUtils.isEmpty(colorRepository.save(color))) {
                return BaseResult.success();
            }
            throw new InternalServerException("Không thể cập nhật màu.");
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }
}
