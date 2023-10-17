package edu.vku.searchjob.repository;

import edu.vku.searchjob.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentsRepository extends JpaRepository<Comments,Integer> {
}
