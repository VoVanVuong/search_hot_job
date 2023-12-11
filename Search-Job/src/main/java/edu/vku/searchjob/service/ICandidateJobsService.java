package edu.vku.searchjob.service;

import edu.vku.searchjob.entity.Cadidates;
import edu.vku.searchjob.entity.CandidateJobs;

import java.util.List;
import java.util.Optional;

public interface ICandidateJobsService {
    public List<CandidateJobs> getCandidateJobsForJob(int id);
    public void applyCandidateJobs(int id);
    public void save(CandidateJobs candidateJobs);
//    public List<CandidateJobs>  finAll();
public List<CandidateJobs> candidateList(Cadidates cadidates);
//    public void findByJobsId(int id);
public CandidateJobs finByIdCanJob(int id);
}
