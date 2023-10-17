package edu.vku.searchjob.repository;

import edu.vku.searchjob.entity.Articles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticlesRepository extends JpaRepository<Articles,Integer> {
}
