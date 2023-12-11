package edu.vku.searchjob.repository;

import edu.vku.searchjob.entity.Cadidates;
import edu.vku.searchjob.entity.CandidateJobs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICandidateJobsRepository extends JpaRepository<CandidateJobs,Integer> {
    List<CandidateJobs> findByJobId(int id);
    List<CandidateJobs> findByCandidate(Cadidates cadidates);
    CandidateJobs getById(int id);
//    CandidateJobs findById(int id);
}
