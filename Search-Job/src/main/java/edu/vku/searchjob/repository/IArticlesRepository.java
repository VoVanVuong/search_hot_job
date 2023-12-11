package edu.vku.searchjob.repository;

import edu.vku.searchjob.entity.Articles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IArticlesRepository extends JpaRepository<Articles,Integer> {
    List<Articles> findByDeleteFlagFalse();
    List<Articles> findByDeleteFlagTrue();
}
