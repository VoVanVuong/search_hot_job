//
////package edu.vku.searchjob.service;
////
////import edu.vku.searchjob.entity.Rating;
////import edu.vku.searchjob.repository.RatingRepository;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Service;
////
////import java.util.List;
////
////@Service
////public class RecommendationDataService {
////
////    private final RatingRepository ratingRepository;
////
////    @Autowired
////    public RecommendationDataService(RatingRepository ratingRepository) {
////        this.ratingRepository = ratingRepository;
////    }
////
////    public List<Rating> getAllRatings() {
////        return ratingRepository.findAll();
////    }
////}
//
//////////////////////////
//package edu.vku.searchjob.service;
//
//import org.apache.mahout.cf.taste.common.TasteException;
//import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
//import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
//import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
//import org.apache.mahout.cf.taste.impl.similarity.CosineSimilarity;
//import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
//import org.apache.mahout.cf.taste.model.DataModel;
//import org.apache.mahout.cf.taste.recommender.RecommendedItem;
//import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
//import org.springframework.stereotype.Service;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
//@Service
//public class RecommendationServiceMahout {
//
//    private final RecommendationDataService recommendationDataService;
//
//    public RecommendationServiceMahout(RecommendationDataService recommendationDataService) {
//        this.recommendationDataService = recommendationDataService;
//    }
//
//    public List<RecommendedItem> trainAndRecommendUserBased() throws IOException, TasteException {
//        List<Rating> ratings = recommendationDataService.getAllRatings();
//        // Xây dựng mô hình từ dữ liệu đánh giá
//        DataModel dataModel = new FileDataModel(RatingDataModelBuilder.buildRatingDataFile(ratings));
//
//        // Xây dựng recommender dựa trên User-Based Collaborative Filtering
//        UserBasedRecommender recommender = new GenericUserBasedRecommender(dataModel, new PearsonCorrelationSimilarity(dataModel));
//        // Lấy 10 sản phẩm được khuyến nghị cho mỗi user (đối với mô hình này)
//        return recommender.recommend(1, 10);
//    }
//
//    public List<RecommendedItem> trainAndRecommendItemBased() throws IOException, TasteException {
//        List<Rating> ratings = recommendationDataService.getAllRatings();
//        // Xây dựng mô hình từ dữ liệu đánh giá
//        DataModel dataModel = new FileDataModel(RatingDataModelBuilder.buildRatingDataFile(ratings));
//
//        // Xây dựng recommender dựa trên Item-Based Collaborative Filtering
//        ItemBasedRecommender recommender = new GenericItemBasedRecommender(dataModel, new CosineSimilarity(dataModel));
//        // Lấy 10 sản phẩm được khuyến nghị cho mỗi user (đối với mô hình này)
//        return recommender.recommend(1, 10);
//    }
//}