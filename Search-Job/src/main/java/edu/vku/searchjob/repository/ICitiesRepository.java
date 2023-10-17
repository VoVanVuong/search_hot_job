package edu.vku.searchjob.repository;

import edu.vku.searchjob.entity.Cities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICitiesRepository extends JpaRepository<Cities,Integer> {
}
