package com.example.microfinancepi.repositories;

import com.example.microfinancepi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
