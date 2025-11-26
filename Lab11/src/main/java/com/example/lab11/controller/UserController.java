package com.example.lab11.controller;

import com.example.lab11.model.User;
import com.example.lab11.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Valid
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/blog/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllUsers(){
       return ResponseEntity.status(200).body(userService.getAllUsers());
    }


    @PostMapping("/add")
    public ResponseEntity<?> addUsers(@Valid @RequestBody User user , Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        userService.addUser(user);
        return ResponseEntity.status(200).body("user is added");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id ,@Valid @RequestBody User user , Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = userService.updateUser(id, user);
        if (!isUpdated){
            return ResponseEntity.status(400).body("user not updated");
        }
       return ResponseEntity.status(200).body("user updated");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        boolean isDeleted = userService.deleteUser(id);
        if (!isDeleted){
            return ResponseEntity.status(400).body("user not deleted");
        }
        return ResponseEntity.status(200).body("user deleted");
    }

    //endpoint7
    @GetMapping("/user/domain")
    public ResponseEntity<?> getUsersByDomain(@RequestParam String domain){
        return ResponseEntity.status(200).body(userService.getUsersByDomain(domain));
    }




}
