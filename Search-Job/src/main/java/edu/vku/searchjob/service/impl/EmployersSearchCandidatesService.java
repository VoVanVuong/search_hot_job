package edu.vku.searchjob.service.impl;

import edu.vku.searchjob.entity.*;
import edu.vku.searchjob.repository.IEmployersSearchCandidatesRepository;
import edu.vku.searchjob.service.IEmployersSearchCandidatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployersSearchCandidatesService implements IEmployersSearchCandidatesService {
    @Autowired
    private IEmployersSearchCandidatesRepository iEmployersSearchCandidatesRepository;

    @Override
    public EmployersSearchCandidates addEmployersSearchCandidates(EmployersSearchCandidates employersSearchCandidates) {

        // Đặt thời gian tạo và cập nhật
//        Date now = new Date();
//        category.setCreatedAt(String.valueOf(now));
//        category.setUpdateAt(String.valueOf(now));
        return iEmployersSearchCandidatesRepository.save(employersSearchCandidates);
    }
    @Override
    public List<EmployersSearchCandidates> listStatusFalse(Cadidates cadidates){
        return iEmployersSearchCandidatesRepository.findByStatusAndCadidates(cadidates);
    }
    @Override
    public List<EmployersSearchCandidates> findByStatusAndCadidatesAndName(Cadidates canCadidates,String name){
        return iEmployersSearchCandidatesRepository.findByStatusAndCadidatesAndName(canCadidates,name);
    }
    @Override
    public List<EmployersSearchCandidates> listStatusTrue(Cadidates cadidates){
        return iEmployersSearchCandidatesRepository.findByStatusAndCadidatesTrue(cadidates);
    }
    @Override
    public List<EmployersSearchCandidates> findByStatusAndCadidatesTrueAndName(Cadidates cadidates,String name,String company){
        return iEmployersSearchCandidatesRepository.findByStatusAndCadidatesTrueAndName(cadidates,name,company);
    }
    @Override
    public List<EmployersSearchCandidates> listStatusAndEmployer(Employers employers){
        return iEmployersSearchCandidatesRepository.findByStatusAndEmployer(employers);
    }
    @Override
    public List<EmployersSearchCandidates> listStatusAndEmployerTrue(Employers employers){
        return iEmployersSearchCandidatesRepository.findByStatusAndEmployerTrue(employers);
    }


    @Override
    public void statusTrue(int id) {
        Optional<EmployersSearchCandidates> jobsOptional = iEmployersSearchCandidatesRepository.findById(id);
        if (jobsOptional.isPresent()) {
            EmployersSearchCandidates employersSearchCandidates = jobsOptional.get();
            employersSearchCandidates.setStatus(true);
            employersSearchCandidates.setStatusInterview(false);
            iEmployersSearchCandidatesRepository.save(employersSearchCandidates);
        }
    }
    @Override
    public void statusfalse(int id) {
        Optional<EmployersSearchCandidates> jobsOptional = iEmployersSearchCandidatesRepository.findById(id);
        if (jobsOptional.isPresent()) {
            EmployersSearchCandidates employersSearchCandidates = jobsOptional.get();
            employersSearchCandidates.setStatus(true);
            employersSearchCandidates.setStatusInterview(true);
            iEmployersSearchCandidatesRepository.save(employersSearchCandidates);
        }
    }
//    @Override
//    public List<EmployersSearchCandidates> listStatusTrue(){
//        return iEmployersSearchCandidatesRepository.findByStatusTrue();
//    }
}
