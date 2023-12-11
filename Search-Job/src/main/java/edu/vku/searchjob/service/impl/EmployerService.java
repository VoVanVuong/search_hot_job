package edu.vku.searchjob.service.impl;

import edu.vku.searchjob.entity.Categories;
import edu.vku.searchjob.entity.Employers;
import edu.vku.searchjob.entity.Jobs;
import edu.vku.searchjob.repository.IEmployersRepository;
import edu.vku.searchjob.service.IEmployersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerService implements IEmployersService {
    @Autowired
    private IEmployersRepository iEmployersRepository;

    @Override
    public List<Employers> findByDeleteFlagFalse() {
        return iEmployersRepository.findByDeleteFlagFalse();
    }

    @Override
    public List<Employers> findByDeleteFlagTrue() {
        return iEmployersRepository.findByDeleteFlagTrue();
    }

    @Override
    public void deleteEmployers(int employersID) {
        Optional<Employers> catgoryOptional = iEmployersRepository.findById(employersID);
        if (catgoryOptional.isPresent()) {
            Employers employers = catgoryOptional.get();
            employers.setDeleteFlag(true);
            iEmployersRepository.save(employers);
        }
    }

    @Override
    public void unDeleteEmployers(int employersID) {
        Optional<Employers> catgoryOptional = iEmployersRepository.findById(employersID);
        if (catgoryOptional.isPresent()) {
            Employers employers = catgoryOptional.get();
            employers.setDeleteFlag(false);
            iEmployersRepository.save(employers);
        }
    }
    @Override
    public List<Employers> finAll(){
        return iEmployersRepository.findAll();
    }

    @Override
    public Employers save(Employers employer) {
        return iEmployersRepository.save(employer);
    }
    @Override
    public List<Employers> getAllEmployers() {
        return iEmployersRepository.findAll();
    }
    @Override
    public Employers getJobById(int id) {
        // Sử dụng phương thức findById của JpaRepository để lấy công việc theo ID
        Optional<Employers> optionalJob = iEmployersRepository.findById(id);

        // Kiểm tra xem công việc có tồn tại hay không
        if (optionalJob.isPresent()) {
            return optionalJob.get();
        } else {
            // Nếu không tìm thấy, bạn có thể xử lý theo ý muốn, ví dụ ném một ngoại lệ
//            throw new ClassNotFoundException("Job not found with id: " + id);
            return null;
        }
    }
//    @Override
//    public Employers getEmployerByAccountId(int accountId) {
//        return iEmployersRepository.findByAccountId(accountId);
//    }
//    @Override
//    public void addEmployerWithAvatar(Employers employer, byte[] avatarData) {
//        employer.setAvatarData(avatarData);
//       iEmployersRepository.save(employer);
//    }
}
