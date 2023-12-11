package edu.vku.searchjob.service;

import edu.vku.searchjob.entity.Employers;

import java.util.List;

public interface IEmployersService {
    List<Employers> findByDeleteFlagFalse();
    List<Employers> findByDeleteFlagTrue();
    //    void markAsDeleted(int categoryId);
    void deleteEmployers(int employersID);
    void unDeleteEmployers(int employersID);
    public List<Employers> finAll();
    public Employers save(Employers employer);
    public List<Employers> getAllEmployers();
    public Employers getJobById(int id);
//    public Employers getEmployerByAccountId(int accountId);
//    public void addEmployerWithAvatar(Employers employer, byte[] avatarData);
}
