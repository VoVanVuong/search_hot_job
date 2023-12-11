package edu.vku.searchjob.repository;

import edu.vku.searchjob.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IAccountRepository extends JpaRepository<Account,Integer> {
//    List<Account> findByEnabledFalse();
    List<Account> findByIsEnabledFalse();
   List<Account> findByIsEnabledTrue();

@Query(value = "SELECT * FROM account where name = ?1", nativeQuery = true)
Optional<Account> findAccountByUserName(String username);

    boolean existsByEmail(String email);
    Account findByEmail(String email);
    Optional<Account> findByName(String name);

    Account findByPrivacy(String enteredPrivacy);
//    @Transactional
//    @Modifying
//    @Query("UPDATE Account a SET a.privacy = :newPrivacy WHERE a.email = :email")
//    void updatePrivacy(@Param("email") String email);
}
