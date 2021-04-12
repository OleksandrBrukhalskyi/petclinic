package com.bov.petclinic.service.impls;

import com.bov.petclinic.dto.RefreshTokenRequest;
import com.bov.petclinic.dto.RegisterRequest;
import com.bov.petclinic.dto.user.UserDto;
import com.bov.petclinic.entity.NotificationEmail;
import com.bov.petclinic.entity.Role;
import com.bov.petclinic.entity.User;
import com.bov.petclinic.entity.VerificationToken;
import com.bov.petclinic.exceptions.BadIdException;
import com.bov.petclinic.repository.RoleRepository;
import com.bov.petclinic.repository.UserRepository;
import com.bov.petclinic.repository.VerificationTokenRepository;
import com.bov.petclinic.security.AuthRequest;
import com.bov.petclinic.security.AuthResponse;
import com.bov.petclinic.security.jwt.JwtProvider;
import com.bov.petclinic.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder, ModelMapper modelMapper,
                           VerificationTokenRepository verificationTokenRepository, MailService mailService, JwtProvider jwtProvider, AuthenticationManager authenticationManager, RefreshTokenService refreshTokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.verificationTokenRepository = verificationTokenRepository;
        this.mailService = mailService;
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public void create(RegisterRequest registerRequest) {
        if(registerRequest == null){
            throw new NullPointerException("User can't be 'null'");
        }
        Role role = roleRepository.findByName("ROLE_USER");
        User toSave = new User();
        toSave.setSurname(registerRequest.getSurname());
        toSave.setFirstname(registerRequest.getFirstname());
        toSave.setPatronymic(registerRequest.getPatronymic());
        toSave.setEmail(registerRequest.getEmail());
        toSave.setLogin(registerRequest.getLogin());
        toSave.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        toSave.setRole(role);
        toSave.setEnabled(false);
        try{
            userRepository.save(toSave);

            String token =generateVerificationToken(toSave);
            mailService.sendMail(new NotificationEmail("Please Activate your Account",
                    registerRequest.getEmail(), "Thank you for signing up to petclinic management system, " +
                    "please click on the below url to activate your account : " +
                    "http://localhost:8080/api/auth/accountVerification/" + token));

        }catch (Exception e){
            log.error(e.getMessage());
        }
        log.info("User [" + toSave.getLogin() + "] created ");

    }
    public AuthResponse login(AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getLogin(),
                authRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateTokenWithAuthentication(authentication);
        return AuthResponse.builder()
                .token(token)
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .login(authRequest.getLogin())
                .build();
    }
    public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateToken(refreshTokenRequest.getLogin());
        return AuthResponse.builder()
                .token(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .login(refreshTokenRequest.getLogin())
                .build();
    }
    @Transactional(readOnly = true)
    public User getCurrentUser(){
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        return userRepository.findByLogin(principal.getUsername());
    }
    public boolean isLoggedIn(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }
    public void verifyAccount(String token){
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        fetchUserAndEnable(verificationToken.orElseThrow(() -> new RuntimeException("Invalid Token")));
    }
    private void fetchUserAndEnable(VerificationToken verificationToken){
        String login = verificationToken.getUser().getLogin();
        User user = userRepository.findByLogin(login);
        user.setEnabled(true);
        userRepository.save(user);
    }
    private String generateVerificationToken(User user){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;

    }

    @Override
    public UserDto update(UserDto user) {
        if(user == null){
            throw new NullPointerException("User can't be 'null'");
        }
        Role role = roleRepository.findByName("ROLE_USER");
        User toUpdate = userRepository.findById(user.getId()).orElseThrow(() -> new BadIdException("Bad id:" + user.getId()));
        toUpdate.setSurname(user.getSurname());
        toUpdate.setFirstname(user.getFirstname());
        toUpdate.setPatronymic(user.getPatronymic());
        toUpdate.setEmail(user.getEmail());
        toUpdate.setLogin(user.getLogin());
        toUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
        toUpdate.setRole(role);
        toUpdate.setEnabled(true);
        try{
            userRepository.save(toUpdate);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        log.info("User [" + toUpdate + "] updated ");
        return modelMapper.map(toUpdate,UserDto.class);
    }

    @Override
    public UserDto getById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BadIdException("Bad id: " + id));
        log.info("User ["+ user.getLogin() +"] found with this id: " + id);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public void delete(long id) {
        try{
            userRepository.deleteById(id);
            log.info("User deleted by this id: " + id);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user,UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        User user = findByLogin(login);
        if(user != null){
            if(passwordEncoder.matches(password,user.getPassword())){
                return user;
            }
        }
        return null;
    }

    @Override
    public Boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }
}
