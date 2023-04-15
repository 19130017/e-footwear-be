package vn.edu.hcmuaf.fit.efootwearspringboot.services.color;

import vn.edu.hcmuaf.fit.efootwearspringboot.dto.color.ColorDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

public interface ColorService {
    public DataResult findAll();
    public DataResult findColor(Long id);

    public BaseResult deleteColor(Long id);

    public BaseResult createColor(ColorDto ColorDto);

    public BaseResult updateColor(ColorDto ColorDto);

}
