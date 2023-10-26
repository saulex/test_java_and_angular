package com.solvedex.test.security.controler;

import com.solvedex.test.model.dto.Message;
import com.solvedex.test.security.converter.UserConverter;
import com.solvedex.test.security.model.dto.JwtDto;
import com.solvedex.test.security.model.dto.LoginUser;
import com.solvedex.test.security.model.dto.NewUser;
import com.solvedex.test.security.enums.RoleName;
import com.solvedex.test.security.model.Role;
import com.solvedex.test.security.model.User;
import com.solvedex.test.security.jwt.JwtProvider;
import com.solvedex.test.security.service.RolseService;
import com.solvedex.test.security.service.UsserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsserService usserService;

    @Autowired
    RolseService rolseService;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserConverter userConverter;


    @PostMapping(value = "/newUser")
    public ResponseEntity<?> newAccess(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new Message("Erro"), HttpStatus.BAD_REQUEST);
        }

        if (usserService.existsByUserName(newUser.getUserName())) {
            return new ResponseEntity<>(new Message("Error name"), HttpStatus.BAD_REQUEST);
        }

        if (usserService.existsByEmail(newUser.getEmail())) {
            return new ResponseEntity<>(new Message("Email exists"), HttpStatus.BAD_REQUEST);
        }

        User user = userConverter.convertNewUserToUser(newUser);
        Set<Role> roles = new HashSet<>();
        roles.add(rolseService.getByRoleName(RoleName.ROLE_USER).get());
        if (newUser.getRoles().contains("admin")) {
            roles.add(rolseService.getByRoleName(RoleName.ROLE_ADMIN).get());
        }
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        usserService.save(user);
        return new ResponseEntity(new Message("New user"), HttpStatus.CREATED);

    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Message("Error"), HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> userLogin = usserService.getByUserName(userDetails.getUsername());
        if (userLogin.isPresent()) {
            JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userLogin.get().getId(), userDetails.getAuthorities());
            return new ResponseEntity<>(jwtDto, HttpStatus.OK);
        }
        return new ResponseEntity(new Message("Error"), HttpStatus.BAD_REQUEST);

    }
}
