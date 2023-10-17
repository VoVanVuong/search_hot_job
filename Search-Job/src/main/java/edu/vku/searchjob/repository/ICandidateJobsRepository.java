package edu.vku.searchjob.repository;

import edu.vku.searchjob.entity.CandidateJobs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICandidateJobsRepository extends JpaRepository<CandidateJobs,Integer> {
}
