package com.bov.petclinic.repository;

import com.bov.petclinic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {
    User findByLogin(String login);
    Boolean existsByLogin(String login);
    User findByEmail(String email);
    Boolean existsByEmail(String email);

}
