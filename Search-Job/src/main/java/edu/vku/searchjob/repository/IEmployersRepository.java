package edu.vku.searchjob.repository;

import edu.vku.searchjob.entity.Employers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployersRepository extends JpaRepository<Employers,Integer> {
}
