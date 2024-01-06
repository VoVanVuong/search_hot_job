package edu.vku.searchjob.service.impl;

import edu.vku.searchjob.entity.Account;
import edu.vku.searchjob.entity.Helps;
import edu.vku.searchjob.repository.IHelpsRepository;
import edu.vku.searchjob.service.IHelpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelpsService implements IHelpsService {
    @Autowired
    private IHelpsRepository iHelpsRepository;
    @Override
    public Helps addHelp(Helps helps, Account account){
        helps.setStatus(false);
        helps.setAccount(account);
        return iHelpsRepository.save(helps);
    }
    @Override
    public List<Helps> findByAccount(Account account){
        return iHelpsRepository.findByAccount(account);
    }
    @Override
    public List<Helps> findByAll(){
        return iHelpsRepository.findAll();
    }
    @Override
    public Helps updateHelp(int id,String reply){
        Helps helps=iHelpsRepository.findById(id).orElse(null);
        if (helps !=null){
            helps.setReply(reply);
            return iHelpsRepository.save(helps);
        }
        return null;
    }
//      @Override
//    public Jobs updateJobs(int id, String name, String address,int totalCandidate,String salary,String deadline,String benefit,String desription) {
//        Jobs existingJob = iJobsRepository.findById(id).orElse(null);
//        if (existingJob != null) {
//            existingJob.setName(name);
//            existingJob.setAddress(address);
//            existingJob.setTotalCandidate(totalCandidate);
//            existingJob.setSalary(salary);
//            existingJob.setDeadline(deadline);
////            existingJob.setCategories();
//            existingJob.setBenefit(benefit);
//            existingJob.setDesription(desription);
////            jobToUpdate.setName(updatedJob.getName());
////
////            jobToUpdate.setAddress(updatedJob.getAddress());
////            jobToUpdate.setTotalCandidate(updatedJob.getTotalCandidate());
////            jobToUpdate.setSalary(updatedJob.getSalary());
////            jobToUpdate.setDeadline(updatedJob.getDeadline());
////            jobToUpdate.setCategories(updatedJob.getCategories());
////            jobToUpdate.setBenefit(updatedJob.getBenefit());
////            jobToUpdate.setDesription(updatedJob.getDesription());
//
//            return iJobsRepository.save(existingJob);
//        }
////        }
//        return null;
//    }
//}

//     @Override
//    public Categories addCategory(Categories category) {
//        category.setDeleteFlag(false);
//        // Đặt thời gian tạo và cập nhật
////        Date now = new Date();
////        category.setCreatedAt(String.valueOf(now));
////        category.setUpdateAt(String.valueOf(now));
//        return iCategoryRepository.save(category);
//    }
}
