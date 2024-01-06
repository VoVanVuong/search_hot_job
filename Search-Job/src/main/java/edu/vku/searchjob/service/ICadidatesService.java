package edu.vku.searchjob.service;

import edu.vku.searchjob.entity.Account;
import edu.vku.searchjob.entity.Cadidates;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface ICadidatesService {
    public List<Cadidates> finAll();
//    public Cadidates finById(int id);
public Cadidates finByAccount(Account id);
    public Cadidates save(Cadidates cadidates);

    public Cadidates updateCandidate(int id, String skill, String dateOfBirth, int phoneNumber, String address, String gender,MultipartFile candidateCv,String cadidatedName,String work,String categotyRequired,String experience,String salaryRequired,Boolean deleteFlag);

    public List<Cadidates> search(String work, String experience, String categoryRequired, String salaryRequired);
    //    public Cadidates updateCandidate(Cadidates cadidates);
    public Cadidates getCandidateUserById(int id);
    public Cadidates candidatesaccount(Account account);
  //  public Cadidates updateCandidate(Cadidates cadidates);
   // public Cadidates updateCandidate(String skill,String dateOfBirth,int phoneNumber,String address,String gender);
}
