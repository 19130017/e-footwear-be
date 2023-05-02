package vn.edu.hcmuaf.fit.efootwearspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.QUERY;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Verify;

import java.util.List;
import java.util.Optional;

public interface VerifyRepository extends JpaRepository<Verify, Long> {
    Optional<Verify> findByToken(String token);
}
