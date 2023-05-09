package vn.edu.hcmuaf.fit.efootwearspringboot.services.detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.detail.DetailDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.detail.DetailSlimDto;
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
            return DataResult.success(detailMapper.toDto(optional.get()));
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
            return DataResult.success(detailMapper.toDtosSlim(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }

    @Override
    public BaseResult createDetail(DetailSlimDto detailSlimDto) {
        Detail detail = detailMapper.toEntitySlim(detailSlimDto);

        if (!ObjectUtils.isEmpty(detailRepository.save(detail))) {
            return BaseResult.success();
        }
        return BaseResult.error(HttpStatus.BAD_REQUEST, "Thêm mới thất bại");
    }


    @Override
    public BaseResult deleteDetail(Long id) {
        Optional<Detail> optional = detailRepository.findById(id);
        if (optional.isPresent()) {
            detailRepository.delete(optional.get());
            return BaseResult.success();
        }
        return BaseResult.error(HttpStatus.BAD_REQUEST, "Xóa thất bại");
    }

    @Override
    public BaseResult updateDetail(DetailSlimDto detailSlimDto) {
        Optional<Detail> optional = detailRepository.findById(detailSlimDto.getId());
        if (optional.isPresent()) {
            Detail detail = optional.get();
            detail.setSize(sizeMapper.toEntity(detailSlimDto.getSize()));
            detail.setProduct(productMapper.slimToEntity(detailSlimDto.getProduct()));
            detail.setStockQuantity(detailSlimDto.getStockQuantity());
            if (!ObjectUtils.isEmpty(detailRepository.save(detail))) {
                return BaseResult.success();
            }

            return BaseResult.error(HttpStatus.BAD_REQUEST, "Cập nhật thất bại");
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }
}
