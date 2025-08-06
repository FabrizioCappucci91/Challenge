package com.challenge.cineMille.service;

import com.challenge.cineMille.model.dto.UserDTO;
import com.challenge.cineMille.model.entity.UserEntity;
import com.challenge.cineMille.model.login.LoginRequest;
import com.challenge.cineMille.model.login.LoginResponse;
import com.challenge.cineMille.repository.UserRepository;
import com.challenge.cineMille.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;


    @Override
    public LoginResponse login(LoginRequest req) {
        return jwtUtil.login(req.getUsername(),req.getPassword());
    }

    @Override
    public void register(UserDTO userDTO) {
        try {
            UserEntity user = new UserEntity();
            user.setNome(userDTO.getNome());
            user.setCognome(userDTO.getCognome());
            user.setUsername(userDTO.getUsername());
            user.setPassword(encoder.encode(userDTO.getPassword()));
            user.setRuoli(userDTO.getRuoli());

            userRepository.save(user);

        } catch (Exception e) {
            throw new RuntimeException("Errore durante la registrazione dell'utente: " + e.getMessage(), e);
        }
    }
}
