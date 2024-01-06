package edu.vku.searchjob.repository;

import edu.vku.searchjob.entity.CandidateJobs;
import edu.vku.searchjob.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMessageReponsitory extends JpaRepository<Message,Integer> {
}
