package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

   
    public User saveUser(@Valid User user) {
        return userRepository.save(user);
    }

    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

   
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

   
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public Optional<User> getUserByName(String name) {
        return userRepository.findBynameOptional(name);
    }
   
    
    public User updateUser(Long id, @Valid User userDetails) {
        userDetails.setId(id);
        return userRepository.save(userDetails);
    }

    
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
