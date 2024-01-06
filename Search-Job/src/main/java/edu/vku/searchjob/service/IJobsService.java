package edu.vku.searchjob.service;

import edu.vku.searchjob.entity.Account;
import edu.vku.searchjob.entity.Categories;
import edu.vku.searchjob.entity.Jobs;

import java.util.List;
import java.util.Optional;

public interface IJobsService {
    List<Jobs> findByDeleteFlagFalse();


    List<Jobs> findByDeleteFlagTrue();
    //    void markAsDeleted(int categoryId);
    void deleteJob(int jobID);
    void undeleteJob(int jobID);

    public void saveJobs(Jobs jobs);
    public List<Jobs> findByAllAccountId(int accountId);
    public Optional<Jobs> getJobsById(int id);
    public Jobs saveJob(Jobs job);
    //public Jobs updateJob(int id, String name,String address,int totalCandidate,String salary,String deadline,Categories categories,String benefit,String desription);
    public List<Jobs> getLatestJobs();
    public List<Jobs>  findLatestThreeJobs();
    public List<Jobs> listJobUser();
    public Jobs getJobById(int id);
    public Jobs updateJobs(int id, String name, String address,int totalCandidate,String salary,String deadline,String benefit,String desription);

    //    public Jobs getJobById(int id);
//    public Optional<Jobs> getJobById(int id);
//    public Jobs saveJob(Jobs job);
    //  public void saveJobs(Jobs jobs,int accountId);
//    List<Jobs> getJobsBasedOnCategoriesDeleteFlag();

//    List<Jobs> getJobsForAccount(String email);

    public List<Jobs> findByAttributesJob(String name,String address);
}
