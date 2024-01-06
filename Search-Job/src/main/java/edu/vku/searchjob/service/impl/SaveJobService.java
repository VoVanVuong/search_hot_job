package edu.vku.searchjob.service.impl;

import edu.vku.searchjob.entity.Account;
import edu.vku.searchjob.entity.Cadidates;
import edu.vku.searchjob.entity.Jobs;
import edu.vku.searchjob.entity.SaveJob;
import edu.vku.searchjob.repository.ISaveJobRepository;
import edu.vku.searchjob.service.ISaveJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaveJobService implements ISaveJobService {
    @Autowired
    private ISaveJobRepository iSaveJobRepository;
    @Override
    public SaveJob saveJob(SaveJob saveJob){
        return iSaveJobRepository.save(saveJob);
    }
    @Override
    public List<SaveJob> findByDeleteFlagFalse(){
        return iSaveJobRepository.findByDeleteFlagFalse();
    }
    @Override
    public List<SaveJob> findByDeleteFlagTrue(){
        return iSaveJobRepository.findAllByDeleteFlagTrue();
    }
    @Override
    public SaveJob findExistingSaveJob(Cadidates candidate, Jobs job) {
        // Assuming you have a repository or some data access mechanism to query the database
        // You need to replace the following line with the actual logic to check for existing SaveJob
        return iSaveJobRepository.findByCandidateAndJob(candidate, job);
    }
    @Override
    public List<SaveJob> findByAccountId(Account account){
        return iSaveJobRepository.findByCandidateAccount(account);
    }
    @Override
    public List<SaveJob> findByAccountIdAndName(Account account,String name){
        return iSaveJobRepository.findByCandidateAccountAndName(account,name);
    }
    @Override
    public void deleteSaveJob(int id) {
        Optional<SaveJob> jobsOptional = iSaveJobRepository.findById(id);
        if (jobsOptional.isPresent()) {
       //     Jobs jobs = jobsOptional.get();
            SaveJob saveJob=jobsOptional.get();
            saveJob.setDeleteFlag(true);
//            jobs.setDeleteFlag(true);
           iSaveJobRepository.save(saveJob);
        }
    }
}
