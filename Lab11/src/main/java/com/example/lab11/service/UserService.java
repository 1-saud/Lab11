package com.example.lab11.service;

import com.example.lab11.model.User;
import com.example.lab11.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
    userRepository.save(user);
    }


    public boolean updateUser(Integer id , User user){
        User oldUser = userRepository.findUserById(id);
    if (oldUser == null){
        return false;
    }

    oldUser.setEmail(user.getEmail());
    oldUser.setId(user.getId());
    oldUser.setPassword(user.getPassword());
    oldUser.setUsername(user.getUsername());
    oldUser.setRegistrationDate(user.getRegistrationDate());
    userRepository.save(oldUser);
return true;
    }

        public boolean deleteUser(Integer id){

        User user =userRepository.findUserById(id);
        if (user == null){
            return false;
        }
        userRepository.delete(user);
        return true;

        }

        //endpointt7
        public List<User> getUsersByDomain(String domain){
            return userRepository.findByEmailEndingWith(domain);
        }

}
