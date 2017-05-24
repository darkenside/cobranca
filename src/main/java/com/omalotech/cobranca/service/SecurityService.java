package com.omalotech.cobranca.service;

public interface SecurityService {
    String findLoggedInUsername();

    int autologin(String username, String password);
}
