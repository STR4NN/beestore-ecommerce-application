package com.example.ecommerce.services;

import com.example.ecommerce.dto.CreateNewUserDTO;
import com.example.ecommerce.dto.LoginRequest;
import com.example.ecommerce.model.Role;
import com.example.ecommerce.model.Usuario;
import com.example.ecommerce.repository.RoleRepository;
import com.example.ecommerce.repository.UsuarioRepository;
import lombok.extern.java.Log;
import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@Service
@RestController
public class UsuarioService {


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<Usuario>> listarUsuarios(){
       return ResponseEntity.ok(usuarioRepository.findAll());

    }

    @PostMapping("/createUser")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody CreateNewUserDTO createNewUserDTO) {
        var basicRole = roleRepository.findByName(Role.Values.BASIC.name());
        var user = usuarioRepository.findByUsername(createNewUserDTO.username());

        if (user.isPresent()) {
            System.out.println("Usuario ja existe");
        }

        if (basicRole == null) {
            Role roleBasicAdd = new Role();
            roleBasicAdd.setName(Role.Values.BASIC.name());
            basicRole = roleRepository.save(roleBasicAdd);
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(createNewUserDTO.username());
        usuario.setPassword(bCryptPasswordEncoder.encode(createNewUserDTO.password()));
         usuario.setRole(Set.of(basicRole));
         usuario.setEmail(createNewUserDTO.email());

        var userCreated = usuarioRepository.save(usuario);
        return ResponseEntity.ok(userCreated);


    }
 }
