package edu.vku.searchjob.repository;

import edu.vku.searchjob.entity.Account;
import edu.vku.searchjob.entity.Categories;
import edu.vku.searchjob.entity.Jobs;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IJobsRepository extends JpaRepository<Jobs,Integer> {
    List<Jobs> findByDeleteFlagFalse();
    List<Jobs> findByDeleteFlagTrue();
    List<Jobs> findByDeleteFlag = null;

    List<Jobs> findByCategories(Categories category);
    List<Jobs> findByCategoriesDeleteFlag(Boolean deleteFlag);
    List<Jobs> findByAccountId(int accountId);
//    List<Jobs> findByAccount(Account account);
@Query("SELECT j FROM Jobs j WHERE j.deleteFlag = false ORDER BY j.createdAt DESC")
List<Jobs> findLatestJobs(Pageable pageable);



}
