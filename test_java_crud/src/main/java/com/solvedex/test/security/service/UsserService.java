package com.solvedex.test.security.service;

import com.solvedex.test.security.model.User;
import com.solvedex.test.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UsserService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<User> getByUserName(String nombreUsuario) {
        return usuarioRepository.findByUserName(nombreUsuario);
    }

    public Boolean existsByUserName(String nombreUsuario) {
        return usuarioRepository.existsByUserName(nombreUsuario);
    }

    public Boolean existsByEmail(String emailUsuario) {
        return usuarioRepository.existsByEmail(emailUsuario);
    }

    public void save(User user) {
        usuarioRepository.save(user);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> user = usuarioRepository.findByUserName(userDetails.getUsername());

        return user.orElse(null);

    }

}
