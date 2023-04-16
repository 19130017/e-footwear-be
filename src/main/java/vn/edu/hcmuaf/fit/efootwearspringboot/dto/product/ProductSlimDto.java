package vn.edu.hcmuaf.fit.efootwearspringboot.dto.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.color.ColorDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.detail.ProductDetailDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product_image.ProductImageDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.size.SizeDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.EntityState;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductSlimDto {
    public Long id;
    private String name;
    private String slug;
    private Integer discountPrice;
    private Integer discountRate;
    private Integer originPrice;
    private Integer colorCounter;
    private List<ProductImageDto> images;
    private Integer sizeCounter;

}
