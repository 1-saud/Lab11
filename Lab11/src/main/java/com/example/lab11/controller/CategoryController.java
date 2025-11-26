package com.example.lab11.controller;

import com.example.lab11.model.Category;
import com.example.lab11.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Valid
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/blog/category")
public class CategoryController {
    private final CategoryService categoryService;


    @GetMapping("/get")
    public ResponseEntity<?> getAllCategories(){
        return ResponseEntity.status(200).body(categoryService.getAllCategories());

    }

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@Valid @RequestBody Category category , Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(message);

        }

        categoryService.addCategory(category);
        return ResponseEntity.status(200).body("category is added");

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id , @Valid @RequestBody Category category , Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);

        }
        boolean isUpdated = categoryService.updateCategory(id, category);
        if (!isUpdated){
            return ResponseEntity.status(400).body("category not updated");
        }

        return ResponseEntity.status(200).body("category updated");
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id){
        boolean isDeleted = categoryService.deleteCategory(id);
        if (!isDeleted){

                return ResponseEntity.status(400).body("category not deleted");
        }

        return ResponseEntity.status(200).body("category deleted");
    }



}

