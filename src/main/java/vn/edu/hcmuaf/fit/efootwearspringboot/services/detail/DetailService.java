package vn.edu.hcmuaf.fit.efootwearspringboot.services.detail;

import vn.edu.hcmuaf.fit.efootwearspringboot.dto.detail.DetailDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.detail.DetailSlimDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

public interface DetailService {
    DataResult findDetailByProduct(Long size_id, String slug, Long color_id);

    DataResult findDetailById(Long id);

    DataResult findAll();

    BaseResult createDetail(DetailSlimDto detailSlimDto);

    BaseResult deleteDetail(Long id);

    BaseResult updateDetail(DetailSlimDto detailSlimDto);
}
