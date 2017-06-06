package com.omalotech.cobranca.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.omalotech.cobranca.model.Role;

public interface RoleRepository extends MongoRepository<Role, Long>{
}
