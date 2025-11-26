package com.example.lab11.repository;

import com.example.lab11.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User , Integer> {

    User findUserById(Integer id);

    //endPoint7
    List<User> findByEmailEndingWith(String domain);
}
