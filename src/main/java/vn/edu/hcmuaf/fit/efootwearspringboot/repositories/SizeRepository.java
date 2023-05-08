package vn.edu.hcmuaf.fit.efootwearspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.QUERY;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Size;

import java.util.List;
import java.util.Optional;

public interface SizeRepository extends JpaRepository<Size, Long> {
    @Query(value = QUERY.SIZE.FIND_SIZE, nativeQuery = true)
    Optional<Size> findSize(Long id);

    @Query(value = QUERY.SIZE.FIND_ALL, nativeQuery = true)
    Optional<List<Size>> findSizes();
}
