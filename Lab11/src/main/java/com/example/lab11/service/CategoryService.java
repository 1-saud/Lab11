package com.example.lab11.service;

import com.example.lab11.model.Category;
import com.example.lab11.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public void addCategory(Category category){
        categoryRepository.save(category);
    }

    public boolean updateCategory(Integer id , Category category){
        Category oldCategory = categoryRepository.findCategoryById(id);
        if (oldCategory == null){
            return false;
        }

        oldCategory.setCategoryName(category.getCategoryName());
        categoryRepository.save(oldCategory);
        return true;
    }

    public boolean deleteCategory(Integer id){
        Category category = categoryRepository.findCategoryById(id);
        if (category == null){
            return false;
        }
        categoryRepository.delete(category);
        return true;
    }

    public List<Category> getCategoriesSortedByPostCount(){
        return categoryRepository.findCategoriesOrderByCountDesc();

    }



}