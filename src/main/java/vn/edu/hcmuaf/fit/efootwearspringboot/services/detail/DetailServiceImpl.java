package vn.edu.hcmuaf.fit.efootwearspringboot.services.detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.efootwearspringboot.exception.NotFoundException;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.DetailMapper;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Detail;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.DetailRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

import java.util.Optional;

@Service
public class DetailServiceImpl implements DetailService {
    private DetailRepository detailRepository;
    private DetailMapper detailMapper;

    @Autowired
    public DetailServiceImpl(DetailRepository detailRepository, DetailMapper detailMapper) {
        this.detailRepository = detailRepository;
        this.detailMapper = detailMapper;
    }

    @Override
    public DataResult findDetailByProduct(Long size_id, String slug, Long color_id) {
        Optional<Detail> optional = detailRepository.findDetailByProduct(size_id, slug, color_id);
        if (optional.isPresent()) {
            return DataResult.success(detailMapper.toSlimDto(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy dữ liệu");
    }
}
