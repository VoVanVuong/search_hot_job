package edu.vku.searchjob.repository;

import edu.vku.searchjob.entity.Account;
import edu.vku.searchjob.entity.Helps;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IHelpsRepository extends JpaRepository<Helps,Integer> {
    List<Helps> findByAccount(Account account);
}
