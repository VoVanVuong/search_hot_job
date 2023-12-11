package edu.vku.searchjob.repository;

import edu.vku.searchjob.entity.Account;
import edu.vku.searchjob.entity.Cadidates;
import edu.vku.searchjob.entity.Employers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICadidatesRepository extends JpaRepository<Cadidates,Integer> {
//    Cadidates findById(int id);
   Cadidates findByAccount(Account id);

   @Query("SELECT j FROM Cadidates j " +
           "WHERE j.deleteFlag = false " +
           "AND (:work IS NULL OR j.work LIKE %:work%) " +
           "AND (:experience IS NULL OR j.experience LIKE %:experience%) " +
           "AND (:categotyRequired IS NULL OR j.categotyRequired LIKE %:categotyRequired%) " +
           "AND (:salaryRequired IS NULL OR j.salaryRequired LIKE %:salaryRequired%)")
   List<Cadidates> findByAttributes(
           @Param("work") String work,
           @Param("experience") String experience,
           @Param("categotyRequired") String categoryRequired,
           @Param("salaryRequired") String salaryRequired
   );

   @Query("SELECT e FROM Cadidates e WHERE e.account = :account")
     Cadidates findByCandidateAccount(@Param("account") Account account);

//   @Query("SELECT j FROM Cadidates j " +
//           "WHERE (:work IS NULL OR j.work LIKE %:work%) " +
//           "AND (:experience IS NULL OR j.experience LIKE %:experience%) " +
//           "AND (:categoryRequired IS NULL OR j.categotyRequired LIKE %:categoryRequired%) " +
//           "AND (:salaryRequired IS NULL OR j.salaryRequired LIKE %:salaryRequired%)")
//   List<Cadidates> findByAttributes(
//           @Param("work") String work,
//           @Param("experience") String experience,
//           @Param("categoryRequired") String categoryRequired,
//           @Param("salaryRequired") String salaryRequired
//   );
}
