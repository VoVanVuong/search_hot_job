package edu.vku.searchjob.repository;

import edu.vku.searchjob.entity.Categories;
import edu.vku.searchjob.entity.Employers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEmployersRepository extends JpaRepository<Employers,Integer> {
    List<Employers> findByDeleteFlagFalse();
//    List<Employers> findAll();
    List<Employers> findByDeleteFlagTrue();
    Employers findByAccountId(int accountId);;
}
