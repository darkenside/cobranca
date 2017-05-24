package com.omalotech.cobranca.service;

import com.omalotech.cobranca.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
