package com.bov.petclinic.security;

import com.bov.petclinic.entity.User;
import com.bov.petclinic.security.jwt.JwtProvider;
import com.bov.petclinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AuthRequest authRequest){
        User user = new User();
        user.setLogin(authRequest.getLogin());
        user.setPassword(authRequest.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(userService.create(user));
    }
    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest authRequest){
        User user = userService.findByLoginAndPassword(authRequest.getLogin(),authRequest.getPassword());
        String token = jwtProvider.generateToken(user.getLogin());
        return new AuthResponse(token);
    }
}
