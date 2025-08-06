package com.challenge.cineMille.configuration;

import com.challenge.cineMille.model.enums.Ruolo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AuthenticationContext {

    private final HttpServletRequest request;

    public Set<Ruolo> getRuoli() {
        Object ruoli = request.getAttribute("jwtRoles");
        if (ruoli instanceof Set<?>) {
            return (Set<Ruolo>) ruoli;
        }
        return Collections.emptySet();
    }

    public boolean isAdmin() {
        return getRuoli().contains(Ruolo.ADMIN);
    }
}
