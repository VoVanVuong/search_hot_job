package edu.vku.searchjob.repository;

import edu.vku.searchjob.entity.JobDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IJobDetailsRepository extends JpaRepository<JobDetails,Integer> {
}
