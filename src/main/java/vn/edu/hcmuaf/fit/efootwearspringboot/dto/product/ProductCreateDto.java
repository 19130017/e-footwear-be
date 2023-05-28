package vn.edu.hcmuaf.fit.efootwearspringboot.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.category.CategoryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.color.ColorDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product_image.ProductImageCreateDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.product_image.ProductImageDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.ProductImage;

import java.util.List;

@Getter
@Setter
public class ProductCreateDto {
    @NotBlank(message = "Không được để trống tên sản phẩm")
    private String name;
    @NotNull(message = "Không được để trống giá sản phẩm")
    private Integer discountRate;
    @NotNull(message = "Không được để trống giá sản phẩm")
    private Integer originPrice;
    @NotBlank(message = "Không được để trống mô tả sản phẩm")
    private String description;
    @NotNull(message = "Không được để trống danh mục sản phẩm")
    private CategoryDto category;
    @NotNull(message = "Không được để trống màu sản phẩm")
    private ColorDto color;
    @NotNull(message = "Không được để trống hình ảnh sản phẩm")
    private List<ProductImageDto> images;
}
