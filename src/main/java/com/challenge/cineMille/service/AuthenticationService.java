package com.challenge.cineMille.service;

import com.challenge.cineMille.model.dto.UserDTO;
import com.challenge.cineMille.model.login.LoginRequest;
import com.challenge.cineMille.model.login.LoginResponse;

public interface AuthenticationService {

    LoginResponse login(LoginRequest req);

    void register(UserDTO dto);
}
