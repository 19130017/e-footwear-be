package vn.edu.hcmuaf.fit.efootwearspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.QUERY;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Detail;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {
    @Query(value = QUERY.DETAIL.FIND_DETAIL_BY_PRODUCT, nativeQuery = true)
    Optional<Detail> findDetailByProduct(Long size_id, String slug, Long color_id);

    @Query(value = QUERY.DETAIL.FIND_DETAILS_BY_PRODUCT, nativeQuery = true)
    Optional<List<Detail>> findDetailsByProduct(String slug, Long color_id);
}
