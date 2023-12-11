package edu.vku.searchjob.service;

import edu.vku.searchjob.entity.Categories;

import java.util.List;

public interface ICategoriesService {
    List<Categories> findByDeleteFlagFalse();
    List<Categories> findByDeleteFlagTrue();
//    void markAsDeleted(int categoryId);
     void deleteVaccine(int catgoryID);
    public Categories addCategory(Categories category);
    public Categories getCategoryById(int id);
//    void updateCategoryName(int id, String name);
    public Categories updateCategoryName(int id, String name);
    List<String> getTop8Categories();
    public void unDeleteVaccine(int catgoryID);

//    public void updateCategory(Categories category);
}