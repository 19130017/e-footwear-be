package vn.edu.hcmuaf.fit.efootwearspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.QUERY;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Collection;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
    @Query(value = QUERY.COLLECTION.FIND_ALL, nativeQuery = true)
    Optional<List<Collection>> findCollections();

    @Query(value = QUERY.COLLECTION.FIND_ALL_WITH_SLUG, nativeQuery = true)
    Optional<List<Collection>> findCollectionsByCate(String slug);

    @Query(value = QUERY.COLLECTION.FIND_COLLECTION, nativeQuery = true)
    Optional<Collection> findCollection(Long id);
}
