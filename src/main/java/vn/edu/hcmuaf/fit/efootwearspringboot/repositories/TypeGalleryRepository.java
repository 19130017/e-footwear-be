package vn.edu.hcmuaf.fit.efootwearspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.QUERY;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.TypeGallery;

import java.util.Optional;
import java.util.List;

public interface TypeGalleryRepository extends JpaRepository<TypeGallery, Long> {
    @Query(value = QUERY.GALLERY_TYPE.FIND_ALL, nativeQuery = true)
    Optional<List<TypeGallery>> findTypeGalleries();

    @Query(value = QUERY.GALLERY_TYPE.FIND_GALLERY_TYPE, nativeQuery = true)
    Optional<TypeGallery> findTypeGalleryBy(Long id);

    @Query(value = QUERY.GALLERY_TYPE.FIND_GALLERY_BY_TYPE, nativeQuery = true)
    Optional<TypeGallery> findTypeGalleryByType(String type);
}
