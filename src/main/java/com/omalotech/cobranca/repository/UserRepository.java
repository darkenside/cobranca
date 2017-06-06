package com.omalotech.cobranca.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.omalotech.cobranca.model.User;

public interface UserRepository extends MongoRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    
    
}
