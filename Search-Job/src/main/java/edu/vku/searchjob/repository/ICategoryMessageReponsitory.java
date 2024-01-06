package edu.vku.searchjob.repository;

import edu.vku.searchjob.entity.CandidateJobs;
import edu.vku.searchjob.entity.CategoryMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryMessageReponsitory extends JpaRepository<CategoryMessage,Integer> {
}
