package edu.vku.searchjob.service;

import edu.vku.searchjob.entity.Articles;

import java.util.List;

public interface IArticlesService {
    List<Articles> findByDeleteFlagFalse();
    List<Articles> findByDeleteFlagTrue();
    void deleteArticles(int articlesID);
    void undeleteArticles(int articlesID);

}
