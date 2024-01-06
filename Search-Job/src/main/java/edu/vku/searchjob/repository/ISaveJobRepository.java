package edu.vku.searchjob.repository;

import edu.vku.searchjob.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISaveJobRepository extends JpaRepository<SaveJob,Integer> {
    List<SaveJob> findByDeleteFlagFalse();
    List<SaveJob> findAllByDeleteFlagTrue();


@Query("SELECT s FROM SaveJob s WHERE s.candidate.account = :account AND s.deleteFlag = true ")
List<SaveJob> findByCandidateAccount(@Param("account") Account account);
@Query("SELECT s FROM SaveJob s WHERE s.candidate.account = :account AND s.deleteFlag = false AND (:name IS NULL OR s.job.name LIKE %:name%)")
List<SaveJob> findByCandidateAccountAndName(@Param("account") Account account, @Param("name") String name);
//    List<SaveJob> findByCandidateAccount(Account account);
    SaveJob findByCandidateAndJob(Cadidates candidate, Jobs job);
}
