package edu.vku.searchjob.service.impl;

import edu.vku.searchjob.entity.Account;
import edu.vku.searchjob.entity.Categories;
import edu.vku.searchjob.entity.Jobs;
import edu.vku.searchjob.repository.ICategoryRepository;
import edu.vku.searchjob.repository.IJobsRepository;
import edu.vku.searchjob.service.IAccountService;
import edu.vku.searchjob.service.IJobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobsService implements IJobsService {

    @Autowired
    private IJobsRepository iJobsRepository;
    @Autowired
    private ICategoryRepository iCategoryRepository;
    //    @Autowired
//    private IAccountRepository iAccountRepository;
    @Autowired
    private IAccountService iAccountService;

    @Autowired
    public JobsService(IJobsRepository iJobsRepository, ICategoryRepository iCategoryRepository) {
        this.iJobsRepository = iJobsRepository;
        this.iCategoryRepository = iCategoryRepository;
    }

    @Override
    public List<Jobs> findByDeleteFlagFalse() {
        return iJobsRepository.findByDeleteFlagFalse();
    }

    @Override
    public List<Jobs> findByDeleteFlagTrue() {
        return iJobsRepository.findByDeleteFlagTrue();
    }

    @Override
    public void deleteJob(int jobID) {
        Optional<Jobs> jobsOptional = iJobsRepository.findById(jobID);
        if (jobsOptional.isPresent()) {
            Jobs jobs = jobsOptional.get();
            jobs.setDeleteFlag(true);
            iJobsRepository.save(jobs);
        }
    }

    @Override
    public void undeleteJob(int jobID) {
        Optional<Jobs> jobsOptional = iJobsRepository.findById(jobID);
        if (jobsOptional.isPresent()) {
            Jobs jobs = jobsOptional.get();
            jobs.setDeleteFlag(false);
            iJobsRepository.save(jobs);
        }
    }

    @Override
    public void saveJobs(Jobs jobs) {
        jobs.setDeleteFlag(false);
//           jobs.setAccount(account);
        iJobsRepository.save(jobs);
    }

    @Override
    public List<Jobs> findByAllAccountId(int accountId) {
        return iJobsRepository.findByAccountId(accountId);
    }

    //    @Override
//    public Jobs getJobById(int id) {
//        return iJobsRepository.findById(id).orElse(null);
//    }
    @Override
    public Optional<Jobs> getJobsById(int id) {
        return iJobsRepository.findById(id);
    }

    @Override
    public Jobs saveJob(Jobs job) {
        return iJobsRepository.save(job);
    }
//    @Override
//    public Jobs updateJob(int id, String name,String address,int totalCandidate,String salary,String deadline,Categories categories,String benefit,String desription) {
//      //  Categories existingCategory = iCategoryRepository.findById(id).orElse(null);
//        Jobs existingJob=iJobsRepository.findById(id).orElse(null);
//        if (existingJob != null) {
//            existingJob.setName(name);
//            existingJob.setAddress(address);
//            existingJob.setTotalCandidate(totalCandidate);
//            existingJob.setSalary(salary);
//            existingJob.setDeadline(deadline);
//            existingJob.setCategories(categories);
//            existingJob.setBenefit(benefit);
//            existingJob.setDesription(desription);
//            return iJobsRepository.save(existingJob);
//        }
////        }
//        return null;
//    }
    @Override
    public List<Jobs> getLatestJobs() {
        PageRequest pageRequest = PageRequest.of(0, 5);
        return iJobsRepository.findLatestJobs( pageRequest);
    }
    @Override
    public List<Jobs>  findLatestThreeJobs() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        return iJobsRepository.findLatestJobs( pageRequest);
    }

    @Override
    public List<Jobs> listJobUser(){
        return iJobsRepository.findByDeleteFlagFalse();
    }
    @Override
    public Jobs getJobById(int id) {
        // Sử dụng phương thức findById của JpaRepository để lấy công việc theo ID
        Optional<Jobs> optionalJob = iJobsRepository.findById(id);

        // Kiểm tra xem công việc có tồn tại hay không
        if (optionalJob.isPresent()) {
            return optionalJob.get();
        } else {
            // Nếu không tìm thấy, bạn có thể xử lý theo ý muốn, ví dụ ném một ngoại lệ
//            throw new ClassNotFoundException("Job not found with id: " + id);
            return null;
        }
    }
    @Override
    public Jobs updateJobs(int id, String name, String address,int totalCandidate,String salary,String deadline,String benefit,String desription) {
        Jobs existingJob = iJobsRepository.findById(id).orElse(null);
        if (existingJob != null) {
            existingJob.setName(name);
            existingJob.setAddress(address);
            existingJob.setTotalCandidate(totalCandidate);
            existingJob.setSalary(salary);
            existingJob.setDeadline(deadline);
//            existingJob.setCategories();
            existingJob.setBenefit(benefit);
            existingJob.setDesription(desription);
//            jobToUpdate.setName(updatedJob.getName());
//
//            jobToUpdate.setAddress(updatedJob.getAddress());
//            jobToUpdate.setTotalCandidate(updatedJob.getTotalCandidate());
//            jobToUpdate.setSalary(updatedJob.getSalary());
//            jobToUpdate.setDeadline(updatedJob.getDeadline());
//            jobToUpdate.setCategories(updatedJob.getCategories());
//            jobToUpdate.setBenefit(updatedJob.getBenefit());
//            jobToUpdate.setDesription(updatedJob.getDesription());

            return iJobsRepository.save(existingJob);
        }
//        }
        return null;
    }
}
//    @Override
//    public Optional<Jobs> getJobById(int id) {
//        return iJobsRepository.findById(id);
//    }
//    @Override
//    public Jobs saveJob(Jobs job) {
//        return iJobsRepository.save(job);
 //   }

   //Route employer
//    @Override
//    public List<Jobs> getJobsForAccount(String email) {
//        Optional<Account> accountOptional = iAccountRepository.findByEmail(email);
//        if (accountOptional.isPresent()) {
//            Account account = accountOptional.get();
//            return iJobsRepository.findByAccount(account);
//        }
//        return Collections.emptyList();
//    }

