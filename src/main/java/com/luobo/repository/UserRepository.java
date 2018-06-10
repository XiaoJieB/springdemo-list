package com.luobo.repository;

import com.luobo.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Long countByFirstNameAndLastName(String firstname,String lastname);
}
