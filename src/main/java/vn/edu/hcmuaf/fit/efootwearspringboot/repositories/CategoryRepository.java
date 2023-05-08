package vn.edu.hcmuaf.fit.efootwearspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.QUERY;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Category;

import java.util.List;
import java.util.Optional;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = QUERY.CATEGORY.FIND_ALL, nativeQuery = true)
    Optional<List<Category>> findCategories();

    @Query(value = QUERY.CATEGORY.FIND_CATEGORY, nativeQuery = true)
    Optional<Category> findCategoryById(Long id);

    @Query(value = QUERY.CATEGORY.FIND_CATEGORY_BY_SLUG, nativeQuery = true)
    Optional<Category> findCategoryBySlug(String slug);
    @Query(value = QUERY.CATEGORY.FIND_PARENT_CATEGORY, nativeQuery = true)
    Optional<List<Category>> findParentCategory();
    @Query(value = QUERY.CATEGORY.FIND_CHILDREN_CATEGORY, nativeQuery = true)
    Optional<List<Category>> findChildrenCategory(Long id);
}
