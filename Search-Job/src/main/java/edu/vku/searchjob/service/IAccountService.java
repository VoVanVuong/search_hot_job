package edu.vku.searchjob.service;

import edu.vku.searchjob.entity.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    List<Account> findByDeleteFlagFalse();
    List<Account> findByDeleteFlagTrue();
    void deleteAccount(int accountID);
    void undeleteAccount(int accountID);
    Optional<Account> findAccountByUserName(String username);
    public Account registerAccount(Account account);
    public boolean isEmailExists(String email);
    public String generateRandomNumber();
    public void updatePassword(String email, String newPassword);
    public boolean isPrivacyValid(String enteredPrivacy);
    public Account finByEmail(String email);
    public Account registerEmployerAccount(Account account);
    public void changePassword(Account account, String newPassword);
    public void changeNowPassword(String email, String currentPassword, String newPassword);
    public void save(Account account);
//    public Integer getUserId();
//    public void updatePrivacyField(int accountId);
//public void updatePrivacyByEmail(String email);

}
