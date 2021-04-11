package com.bov.petclinic.security;

import com.bov.petclinic.dto.RegisterRequest;
import com.bov.petclinic.security.jwt.JwtProvider;
import com.bov.petclinic.service.impls.RefreshTokenService;
import com.bov.petclinic.service.impls.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class AuthController {
    private final UserServiceImpl userService;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public AuthController(UserServiceImpl userService, JwtProvider jwtProvider, RefreshTokenService refreshTokenService) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
        this.refreshTokenService = refreshTokenService;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest registerRequest){
        if(userService.existsByLogin(registerRequest.getLogin())){
            return new ResponseEntity<>("Login is already taken",HttpStatus.BAD_REQUEST);
        }
        userService.create(registerRequest);
        return new ResponseEntity<>("You have registered!",HttpStatus.CREATED);

    }
    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest authRequest){
//        User user = userService.findByLoginAndPassword(authRequest.getLogin(),authRequest.getPassword());
//        String token = jwtProvider.generateToken(user.getLogin());
        return userService.login(authRequest);
    }
}
