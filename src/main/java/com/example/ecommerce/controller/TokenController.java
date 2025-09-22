package com.example.ecommerce.controller;

import com.example.ecommerce.dto.LoginRequest;
import com.example.ecommerce.dto.LoginResponse;
import com.example.ecommerce.model.Usuario;
import com.example.ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class TokenController {

    private final JwtEncoder jwtEncoder;
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public TokenController(JwtEncoder jwtEncoder, UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.usuarioRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUsers (@RequestBody LoginRequest loginRequest){
       var user= usuarioRepository.findByUsername(loginRequest.username());

       if(user.isEmpty() || !user.get().isLoginCorrect(loginRequest, bCryptPasswordEncoder)){
           throw new BadCredentialsException("Invalido, usuario não existe.");
       }

       var now = Instant.now();
       var expiresIn = 300L;


       var claims = JwtClaimsSet.builder()
               .issuer("beestore")
               .subject(user.get().getId().toString())
               .expiresAt(now.plusSeconds(expiresIn))
               .issuedAt(now).build();

       var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
       return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));

    }
}
