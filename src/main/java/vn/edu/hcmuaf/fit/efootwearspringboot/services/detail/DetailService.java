package vn.edu.hcmuaf.fit.efootwearspringboot.services.detail;

import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

public interface DetailService {
     DataResult findDetailByProduct(Long size_id, String slug, Long color_id);

}
