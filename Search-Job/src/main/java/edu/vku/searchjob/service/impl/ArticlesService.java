package edu.vku.searchjob.service.impl;

import edu.vku.searchjob.entity.Articles;
import edu.vku.searchjob.entity.Employers;
import edu.vku.searchjob.repository.IArticlesRepository;
import edu.vku.searchjob.service.IArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticlesService implements IArticlesService {

    @Autowired
    private IArticlesRepository iArticlesRepository;

    @Override
    public List<Articles> findByDeleteFlagFalse() {
        return iArticlesRepository.findByDeleteFlagFalse();
    }

    @Override
    public List<Articles> findByDeleteFlagTrue() {
        return iArticlesRepository.findByDeleteFlagTrue();
    }

    @Override
    public void deleteArticles(int articlesID) {
        Optional<Articles> articlesOptional = iArticlesRepository.findById(articlesID);
        if (articlesOptional.isPresent()) {
            Articles articles = articlesOptional.get();
            articles.setDeleteFlag(true);
            iArticlesRepository.save(articles );
        }
    }
    @Override
    public void undeleteArticles(int articlesID) {
        Optional<Articles> articlesOptional=iArticlesRepository.findById(articlesID);
        if(articlesOptional.isPresent()){
            Articles articles=articlesOptional.get();
            articles.setDeleteFlag(false);
            iArticlesRepository.save(articles);
        }

    }
}
