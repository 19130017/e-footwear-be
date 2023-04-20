package vn.edu.hcmuaf.fit.efootwearspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.QUERY;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Gallery;

import java.util.List;
import java.util.Optional;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {
    @Query(value = QUERY.GALLERY.FIND_GALLERIES_BY_TYPE, nativeQuery = true)
    Optional<List<Gallery>> findGalleriesByType(String type_code);
    @Query(value = QUERY.GALLERY.FIND_CAROUSELS, nativeQuery = true)
    Optional<List<Gallery>> findCarousels();
    @Query(value = QUERY.GALLERY.FIND_COLLECTIONS, nativeQuery = true)
    Optional<List<Gallery>> findCollections();
    @Query(value = QUERY.GALLERY.FIND_BANNERS, nativeQuery = true)
    Optional<List<Gallery>> findBanners();
    @Query(value = QUERY.GALLERY.FIND_ADS, nativeQuery = true)
    Optional<List<Gallery>> findAds();
    @Query(value = QUERY.GALLERY.FIND_FOOTER, nativeQuery = true)
    Optional<List<Gallery>> findFooters();
}
