package vn.edu.hcmuaf.fit.efootwearspringboot.services.size;

import vn.edu.hcmuaf.fit.efootwearspringboot.dto.size.SizeDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

public interface SizeService {
    public DataResult findAll();
    public DataResult findSize(Long id);

    public BaseResult deleteSize(Long id);

    public BaseResult createSize(SizeDto SizeDto);

    public BaseResult updateSize(SizeDto SizeDto);
}
