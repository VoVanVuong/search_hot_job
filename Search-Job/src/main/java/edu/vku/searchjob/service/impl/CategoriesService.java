package edu.vku.searchjob.service.impl;

import edu.vku.searchjob.entity.Categories;
import edu.vku.searchjob.repository.ICategoryRepository;
import edu.vku.searchjob.service.ICategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
@Service
public class CategoriesService implements ICategoriesService {
    @Autowired
    private ICategoryRepository iCategoryRepository;

    @Autowired
    public CategoriesService(ICategoryRepository iCategoryRepository) {
        this.iCategoryRepository = iCategoryRepository;
    }

    @Override
    public List<Categories> findByDeleteFlagFalse() {
        return iCategoryRepository.findByDeleteFlagFalse();
    }

    @Override
    public List<Categories> findByDeleteFlagTrue() {
        return iCategoryRepository.findByDeleteFlagTrue();
    }

    @Override
    public void deleteVaccine(int catgoryID) {
        Optional<Categories> catgoryOptional = iCategoryRepository.findById(catgoryID);
        if (catgoryOptional.isPresent()) {
            Categories categories = catgoryOptional.get();
            categories.setDeleteFlag(true);
            iCategoryRepository.save(categories);
        }
    }
    @Override
    public void unDeleteVaccine(int catgoryID) {
        Optional<Categories> catgoryOptional = iCategoryRepository.findById(catgoryID);
        if (catgoryOptional.isPresent()) {
            Categories categories = catgoryOptional.get();
            categories.setDeleteFlag(false);
            iCategoryRepository.save(categories);
        }
    }

    @Override
    public Categories addCategory(Categories category) {
        category.setDeleteFlag(false);
        // Đặt thời gian tạo và cập nhật
//        Date now = new Date();
//        category.setCreatedAt(String.valueOf(now));
//        category.setUpdateAt(String.valueOf(now));
        return iCategoryRepository.save(category);
    }

    @Override
    public Categories getCategoryById(int id) {
        return iCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public Categories updateCategoryName(int id, String name) {
        Categories existingCategory = iCategoryRepository.findById(id).orElse(null);
            if (existingCategory != null) {
                existingCategory.setName(name);
                return iCategoryRepository.save(existingCategory);
            }
//        }
        return null;
    }
    @Override
    public List<String> getTop8Categories() {
        PageRequest pageRequest = PageRequest.of(0, 5);
        return iCategoryRepository.findTop8Categories(pageRequest);
    }
}