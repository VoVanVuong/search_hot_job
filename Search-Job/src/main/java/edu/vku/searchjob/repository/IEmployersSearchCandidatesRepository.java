package edu.vku.searchjob.repository;

import edu.vku.searchjob.entity.Cadidates;
import edu.vku.searchjob.entity.Employers;
import edu.vku.searchjob.entity.EmployersSearchCandidates;
import edu.vku.searchjob.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEmployersSearchCandidatesRepository extends JpaRepository<EmployersSearchCandidates,Integer> {


//    List<EmployersSearchCandidates> findByStatusFalse();
//    List<EmployersSearchCandidates> findByStatusTrue();
@Query("SELECT esc FROM EmployersSearchCandidates esc WHERE esc.status = false AND esc.cadidates = :cadidates")
List<EmployersSearchCandidates> findByStatusAndCadidates(@Param("cadidates") Cadidates cadidates);

    @Query("SELECT esc FROM EmployersSearchCandidates esc WHERE esc.status = true AND esc.cadidates = :cadidates")
    List<EmployersSearchCandidates> findByStatusAndCadidatesTrue(@Param("cadidates") Cadidates cadidates);

    @Query("SELECT esc FROM EmployersSearchCandidates esc WHERE esc.status = false AND esc.employers = :employers")
    List<EmployersSearchCandidates> findByStatusAndEmployer(@Param("employers") Employers employers);
    @Query("SELECT esc FROM EmployersSearchCandidates esc WHERE esc.status = true AND esc.employers = :employers")
    List<EmployersSearchCandidates> findByStatusAndEmployerTrue(@Param("employers") Employers employers);

}
