package vn.edu.hcmuaf.fit.efootwearspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.QUERY;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Color;

import java.util.List;
import java.util.Optional;

public interface ColorRepository extends JpaRepository<Color, Long> {

    @Query(value = QUERY.COLOR.FIND_COLOR, nativeQuery = true)
    Optional<Color> findColor(Long id);

    @Query(value = QUERY.COLOR.FIND_ALL, nativeQuery = true)
    Optional<List<Color>> findColors();
}
