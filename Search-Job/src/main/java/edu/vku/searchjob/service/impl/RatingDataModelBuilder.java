package edu.vku.searchjob.service.impl;
import edu.vku.searchjob.entity.Jobs;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
public class RatingDataModelBuilder {

    public static File buildRatingDataFile(List<Jobs> ratings) throws IOException {
        // Tạo một tệp tạm thời để lưu dữ liệu đánh giá
        File tempFile = File.createTempFile("ratings", ".csv");

        try (FileWriter fileWriter = new FileWriter(tempFile)) {
            // Ghi dữ liệu đánh giá vào tệp
            for (Jobs rating : ratings) {
//                fileWriter.write(String.format("%d,%d,%.1f%n", rating.getUserId(), rating.getItemId(), rating.getValue()));
                fileWriter.write(String.format("%d,%d,%.1f%n", rating.getId(), rating.getName(), rating.getAddress()));
            }
        }

        // Trả về tệp dữ liệu đã được xây dựng
        return tempFile;
    }

}
//import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.List;
//
//public class RatingDataModelBuilder {
//
//    public static File buildRatingDataFile(List<Rating> ratings) throws IOException {
//        // Tạo một tệp tạm thời để lưu dữ liệu đánh giá
//        File tempFile = File.createTempFile("ratings", ".csv");
//
//        try (FileWriter fileWriter = new FileWriter(tempFile)) {
//            // Ghi dữ liệu đánh giá vào tệp
//            for (Rating rating : ratings) {
//                fileWriter.write(String.format("%d,%d,%.1f%n", rating.getUserId(), rating.getItemId(), rating.getValue()));
//            }
//        }
//
//        // Trả về tệp dữ liệu đã được xây dựng
//        return tempFile;
//    }
//}