package com.solvedex.test.security.service;

import com.solvedex.test.security.model.User;
import com.solvedex.test.security.model.MainUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailImpl implements UserDetailsService {
    @Autowired
    private UsserService usserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usserService.getByUserName(username).get();
        return MainUser.buil(user);
    }
}
