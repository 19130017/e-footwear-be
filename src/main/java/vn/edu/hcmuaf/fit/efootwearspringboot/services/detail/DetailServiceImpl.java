package vn.edu.hcmuaf.fit.efootwearspringboot.services.detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.detail.DetailDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.detail.DetailSlimDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.exception.InternalServerException;
import vn.edu.hcmuaf.fit.efootwearspringboot.exception.NotFoundException;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.DetailMapper;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.ProductMapper;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.SizeMapper;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Detail;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.DetailRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

import java.util.List;
import java.util.Optional;

@Service
public class DetailServiceImpl implements DetailService {
    private DetailRepository detailRepository;
    private DetailMapper detailMapper;
    private SizeMapper sizeMapper;
    private ProductMapper productMapper;

    @Autowired
    public DetailServiceImpl(DetailRepository detailRepository, DetailMapper detailMapper, SizeMapper sizeMapper, ProductMapper productMapper) {
        this.detailRepository = detailRepository;
        this.detailMapper = detailMapper;
        this.sizeMapper = sizeMapper;
        this.productMapper = productMapper;
    }

    @Override
    public DataResult findDetailByProduct(Long size_id, String slug, Long color_id) {
        Optional<Detail> optional = detailRepository.findDetailByProduct(size_id, slug, color_id);
        if (optional.isPresent()) {
            return DataResult.success(detailMapper.toSlimDto(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public DataResult findDetailById(Long id) {
        Optional<Detail> optional = detailRepository.findById(id);
        if (optional.isPresent()) {
            return DataResult.success(detailMapper.toDto(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public DataResult findAll() {
        Optional<List<Detail>> optional = detailRepository.findDetails();
        if (optional.isPresent()) {
            return DataResult.success(detailMapper.toDtos(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public BaseResult createDetail(DetailDto detailDto) {
        Detail detail = detailMapper.toEntity(detailDto);

        if (!ObjectUtils.isEmpty(detailRepository.save(detail))) {
            return BaseResult.success();
        }
        throw new InternalServerException("Không thể thêm mới chi tiết sản phẩm.");
    }


    @Override
    public BaseResult deleteDetail(Long id) {
        Optional<Detail> optional = detailRepository.findById(id);
        if (optional.isPresent()) {
            detailRepository.delete(optional.get());
            return BaseResult.success();
        }
        throw new NotFoundException("Không tìm thấy chi tiết sản phẩm.");
    }

    @Override
    public BaseResult updateDetail(DetailDto detailDto) {
        Optional<Detail> optional = detailRepository.findById(detailDto.getId());
        if (optional.isPresent()) {
            Detail detail = detailMapper.toEntity(detailDto);
            if (!ObjectUtils.isEmpty(detailRepository.save(detail))) {
                return BaseResult.success();
            }
            throw new InternalServerException("Không thể cập nhật chi tiết sản phẩm.");
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }
}
