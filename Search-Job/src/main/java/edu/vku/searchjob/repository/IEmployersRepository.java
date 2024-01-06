package edu.vku.searchjob.repository;

import edu.vku.searchjob.entity.Account;
import edu.vku.searchjob.entity.Cadidates;
import edu.vku.searchjob.entity.Categories;
import edu.vku.searchjob.entity.Employers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEmployersRepository extends JpaRepository<Employers,Integer> {
    List<Employers> findByDeleteFlagFalse();
//    List<Employers> findAll();
    List<Employers> findByDeleteFlagTrue();
    Employers findByAccountId(int accountId);
    @Query("SELECT e.account.id FROM Employers e")
    List<Integer> findAllIds();
    Employers findByAccountId(Account account);



    @Query("SELECT e FROM Employers e WHERE e.account = :account")
    Employers findByAccount(@Param("account") Account account);

    @Query("SELECT j FROM Employers j " +
            "WHERE j.deleteFlag = true " +
            "AND (:name IS NULL OR j.name LIKE %:name%)")
    List<Employers> findByAttributesWord(
            @Param("name") String name
    );



}
