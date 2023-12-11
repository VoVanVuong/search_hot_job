package edu.vku.searchjob.repository;

import edu.vku.searchjob.entity.Account;
import edu.vku.searchjob.entity.Cadidates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICadidatesRepository extends JpaRepository<Cadidates,Integer> {
//    Cadidates findById(int id);
   Cadidates findByAccount(Account id);
}
