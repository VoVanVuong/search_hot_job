package edu.vku.searchjob.repository;

import edu.vku.searchjob.entity.Categories;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
public interface ICategoryRepository extends JpaRepository<Categories,Integer> {
    List<Categories> findByDeleteFlagFalse();

    List<Categories> findByDeleteFlagTrue();
    @Query("SELECT c.name FROM Categories c WHERE c.deleteFlag = false ORDER BY c.createdAt DESC")
    List<String> findTop8Categories(Pageable pageable);

//    List<Categories> findByDeleteFlag(Boolean deleteFlagValue);
      List<Categories> findByDeleteFlag(Boolean deleteFlag);
//    @Modifying
//    @Transactional
//    @Query("UPDATE Categories c SET c.name = :name WHERE c.id = :id")
//    void updateCategoryName(@Param("id") int id, @Param("name") String name);


}
