package com.omalotech.cobranca.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.omalotech.cobranca.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
