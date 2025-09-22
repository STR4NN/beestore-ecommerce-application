package com.example.ecommerce.controller;

import com.example.ecommerce.model.Role;
import com.example.ecommerce.model.Usuario;

import com.example.ecommerce.repository.RoleRepository;
import com.example.ecommerce.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {


    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminUserConfig(UsuarioRepository usuarioRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional // Ou tudo roda ou nada roda
    public void run(String... args) throws Exception {

        Role finalRoleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());

        var userAdmin = usuarioRepository.findByUsername("admin");

        userAdmin.ifPresentOrElse(
                (user) -> {
                    System.out.println("O admin ja existe no banco.");
                },

                () -> {
                    Usuario admin = new Usuario();
                    admin.setUsername("admin");
                    admin.setPassword(bCryptPasswordEncoder.encode("123"));
                    admin.setRole(Set.of(finalRoleAdmin));

                    usuarioRepository.save(admin);
                }
        );
//
    }
}
