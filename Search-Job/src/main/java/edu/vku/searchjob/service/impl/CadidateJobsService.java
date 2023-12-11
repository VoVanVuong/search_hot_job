package edu.vku.searchjob.service.impl;

import edu.vku.searchjob.entity.Cadidates;
import edu.vku.searchjob.entity.CandidateJobs;
import edu.vku.searchjob.repository.ICandidateJobsRepository;
import edu.vku.searchjob.service.ICandidateJobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadidateJobsService implements ICandidateJobsService {
    @Autowired
    private ICandidateJobsRepository iCandidateJobsRepository;
    @Override
    public List<CandidateJobs> getCandidateJobsForJob(int id) {
        return iCandidateJobsRepository.findByJobId(id);
    }
    @Override
    public void applyCandidateJobs(int id) {
        Optional<CandidateJobs> candidateJobsOptional = iCandidateJobsRepository.findById(id);
        if (candidateJobsOptional.isPresent()) {
            CandidateJobs candidateJobs= candidateJobsOptional.get();
            candidateJobs.setStatus(true);

            iCandidateJobsRepository.save( candidateJobs);
        }
    }
    @Override
    public void save(CandidateJobs candidateJobs) {
        iCandidateJobsRepository.save(candidateJobs);
    }
//    @Override
//    public List<CandidateJobs> finAll(){
//        return iCandidateJobsRepository.findAll();
//    }
    @Override
    public List<CandidateJobs> candidateList(Cadidates cadidates){
        return iCandidateJobsRepository.findByCandidate(cadidates);
    }
//    @Override
// public void findByJobsId(int id){
//        Optional<CandidateJobs> candidateJobsOptional = iCandidateJobsRepository.findById(id);
//        if (candidateJobsOptional.isPresent()) {
//            CandidateJobs candidateJobs= candidateJobsOptional.get();
//              candidateJobs.getJob().getId();
//        }
// }
@Override
    public CandidateJobs finByIdCanJob(int id){
        return iCandidateJobsRepository.getById(id);
    }
}
