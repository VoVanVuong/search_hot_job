package edu.vku.searchjob.repository;

import edu.vku.searchjob.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IJobsRepository extends JpaRepository<Jobs,Integer> {
}
