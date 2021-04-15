package com.bov.petclinic.security;

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
@RequestMapping("/api/auth")
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
        return new ResponseEntity<>("You have registered! Confirmation email was sent to your email",HttpStatus.CREATED);

    }
    @PostMapping("/sign-in")
    public AuthResponse auth(@RequestBody AuthRequest authRequest){
        return userService.login(authRequest);
    }
    @PostMapping("/refresh/token")
    public AuthResponse refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return userService.refreshToken(refreshTokenRequest);
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body("Refresh Token Deleted Successfully!!");
    }
    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        userService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully", HttpStatus.OK);
    }
}
