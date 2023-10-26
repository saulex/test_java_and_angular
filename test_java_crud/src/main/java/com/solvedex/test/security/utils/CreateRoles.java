package com.solvedex.test.security.utils;

import com.solvedex.test.security.enums.RoleName;
import com.solvedex.test.security.model.Role;
import com.solvedex.test.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        Role roleAdmin = new Role(RoleName.ROLE_ADMIN);
        Role roleUser = new Role(RoleName.ROLE_USER);
        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);


    }

}
