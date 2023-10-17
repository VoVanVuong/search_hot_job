package edu.vku.searchjob.repository;

import edu.vku.searchjob.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account,Integer> {
}
