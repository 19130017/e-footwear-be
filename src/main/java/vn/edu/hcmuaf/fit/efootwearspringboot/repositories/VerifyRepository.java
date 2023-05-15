package vn.edu.hcmuaf.fit.efootwearspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.QUERY;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Verify;

import java.util.List;
import java.util.Optional;

public interface VerifyRepository extends JpaRepository<Verify, Long> {
    @Query(value = QUERY.VERIFY.FIND_TOKEN, nativeQuery = true)
    Optional<Verify> findByToken(String token);
    @Query(value = QUERY.VERIFY.FIND_BY_ACCOUNT_AND_TYPE, nativeQuery = true)

    Optional<Verify> findByAccountIdAndType(Long id, String verify);
}
