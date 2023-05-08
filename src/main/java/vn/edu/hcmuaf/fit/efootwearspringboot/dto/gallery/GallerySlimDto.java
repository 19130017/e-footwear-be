package vn.edu.hcmuaf.fit.efootwearspringboot.dto.gallery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GallerySlimDto {
    private Long id;
    private String imageURL;
    private String link;
    private String title;
}
