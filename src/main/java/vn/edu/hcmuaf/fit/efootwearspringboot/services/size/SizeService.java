package vn.edu.hcmuaf.fit.efootwearspringboot.services.size;

import vn.edu.hcmuaf.fit.efootwearspringboot.dto.size.SizeDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

 public interface SizeService {
     DataResult findAll();
     DataResult findSize(Long id);

     BaseResult deleteSize(Long id);

     BaseResult createSize(SizeDto SizeDto);

     BaseResult updateSize(SizeDto SizeDto);
}
