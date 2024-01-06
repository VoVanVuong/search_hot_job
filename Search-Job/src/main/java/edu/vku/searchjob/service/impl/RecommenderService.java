//package edu.vku.searchjob.service.impl;
//import edu.vku.searchjob.entity.Jobs;
//import edu.vku.searchjob.service.IJobsService;
//
//import org.apache.mahout.cf.taste.common.TasteException;
//import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
//import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
//import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
//import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
//import org.apache.mahout.cf.taste.model.DataModel;
//import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
//import org.apache.mahout.cf.taste.recommender.RecommendedItem;
//import org.apache.mahout.cf.taste.similarity.UserSimilarity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.security.Principal;
//import java.util.ArrayList;
//import java.util.List;
//@Service
//public class RecommenderService {
//    private final GenericUserBasedRecommender recommender;
//    @Autowired
//    private IJobsService iJobsService;
//
//    @Autowired
//    public RecommenderService() throws IOException, TasteException {
//        this.recommender = buildRecommender();
//    }
//
//
//    private GenericUserBasedRecommender buildRecommender() throws IOException, TasteException {
//        List<Jobs> jobsList = iJobsService.findByDeleteFlagFalse();
//        DataModel dataModel = new FileDataModel(RatingDataModelBuilder.buildRatingDataFile(jobsList));
//
//        UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(dataModel);
//        UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(10, userSimilarity, dataModel);
//
//        return new GenericUserBasedRecommender(dataModel, userNeighborhood, userSimilarity);
//    }
//
//    public List<RecommendedItem> getRecommendationsForUser(int userId, int numberOfRecommendations) throws TasteException {
//        return recommender.recommend(userId, numberOfRecommendations);
//    }
//}
