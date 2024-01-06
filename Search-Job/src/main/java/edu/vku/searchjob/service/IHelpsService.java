package edu.vku.searchjob.service;

import edu.vku.searchjob.entity.Account;
import edu.vku.searchjob.entity.Helps;

import java.util.List;

public interface IHelpsService {
    public Helps addHelp(Helps helps, Account account);
    public List<Helps> findByAccount(Account account);
    public List<Helps> findByAll();
    public Helps updateHelp(int id,String reply);
}
