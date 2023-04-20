package vn.edu.hcmuaf.fit.efootwearspringboot.services.color;

import vn.edu.hcmuaf.fit.efootwearspringboot.dto.color.ColorDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

public interface ColorService {
     DataResult findAll();
     DataResult findColor(Long id);

     BaseResult deleteColor(Long id);

     BaseResult createColor(ColorDto ColorDto);

     BaseResult updateColor(ColorDto ColorDto);

}
