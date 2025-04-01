package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    
  
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);
    

      @Query("SELECT u FROM User u WHERE u.name = :name")
      Optional<User> findBynameOptional(@Param("name") String name);
}
