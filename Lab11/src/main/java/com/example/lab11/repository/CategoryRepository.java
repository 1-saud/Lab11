package com.example.lab11.repository;

import com.example.lab11.model.Category;
import com.example.lab11.model.Post;
import com.example.lab11.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findCategoryById(Integer id);




    //EndPoint6
    @Query("select p.category from Post p group by p.category order by count(p) desc")
    List<Category> findCategoriesOrderByCountDesc();
}
