package edu.vku.searchjob.repository;

import edu.vku.searchjob.entity.Cadidates;
import edu.vku.searchjob.entity.Categories;
import edu.vku.searchjob.entity.Jobs;
import edu.vku.searchjob.entity.SaveJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISaveJobRepository extends JpaRepository<SaveJob,Integer> {
    List<SaveJob> findByDeleteFlagFalse();
    List<SaveJob> findAllByDeleteFlagTrue();
    SaveJob findByCandidateAndJob(Cadidates candidate, Jobs job);
}
