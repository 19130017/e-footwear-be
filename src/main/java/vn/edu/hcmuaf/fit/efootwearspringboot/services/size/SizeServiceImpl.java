package vn.edu.hcmuaf.fit.efootwearspringboot.services.size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.size.SizeDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.exception.InternalServerException;
import vn.edu.hcmuaf.fit.efootwearspringboot.exception.NotFoundException;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.SizeMapper;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Size;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.SizeRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.EntityState;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

import java.util.List;
import java.util.Optional;


@Service
public class SizeServiceImpl implements SizeService {
    private SizeRepository sizeRepository;
    private SizeMapper sizeMapper;

    @Autowired
    public SizeServiceImpl(SizeRepository sizeRepository, SizeMapper sizeMapper) {
        this.sizeRepository = sizeRepository;
        this.sizeMapper = sizeMapper;
    }

    @Override
    public DataResult findAll() {
        Optional<List<Size>> optional = sizeRepository.findSizes();
        if (optional.isPresent()) {
            return DataResult.success(sizeMapper.toDtos(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public DataResult findSize(Long id) {
        Optional<Size> optional = sizeRepository.findSize(id);
        if (optional.isPresent()) {
            return DataResult.success(sizeMapper.toDto(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public BaseResult deleteSize(Long id) {
        Optional<Size> optional = sizeRepository.findById(id);
        if (optional.isPresent()) {
            Size size = optional.get();
            size.setState(EntityState.DELETED);
            if (!ObjectUtils.isEmpty(sizeRepository.save(size))) {
                return BaseResult.success();
            }
            throw new InternalServerException("Không thể xoá size");
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public BaseResult createSize(SizeDto sizeDto) {
        Size size = sizeMapper.toEntity(sizeDto);
        size.setState(EntityState.ACTIVE);
        if (!ObjectUtils.isEmpty(sizeRepository.save(size))) {
            return BaseResult.success();
        }
        throw new InternalServerException("Không thêm mới size");
    }

    @Override
    public BaseResult updateSize(SizeDto sizeDto) {
        Optional<Size> optional = sizeRepository.findById(sizeDto.getId());
        if (optional.isPresent()) {
            Size size = optional.get();
            size.setValue(sizeDto.getValue());
            if (!ObjectUtils.isEmpty(sizeRepository.save(size))) {
                return BaseResult.success();
            }
            throw new InternalServerException("Không cập nhật size");
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }
}
