package edu.vku.searchjob.service;

import edu.vku.searchjob.entity.Account;
import edu.vku.searchjob.entity.Cadidates;
import edu.vku.searchjob.entity.Jobs;
import edu.vku.searchjob.entity.SaveJob;

import java.util.List;

public interface ISaveJobService {
    public SaveJob saveJob(SaveJob saveJob);
    public List<SaveJob> findByDeleteFlagFalse();
    public List<SaveJob> findByDeleteFlagTrue();
    public SaveJob findExistingSaveJob(Cadidates candidate, Jobs job);
    public void deleteSaveJob(int id);
    public List<SaveJob> findByAccountId(Account account);
    public List<SaveJob> findByAccountIdAndName(Account account,String name);
}
