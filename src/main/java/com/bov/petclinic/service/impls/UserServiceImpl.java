package com.bov.petclinic.service.impls;

import com.bov.petclinic.dto.user.UserDto;
import com.bov.petclinic.entity.Role;
import com.bov.petclinic.entity.User;
import com.bov.petclinic.exceptions.BadIdException;
import com.bov.petclinic.repository.RoleRepository;
import com.bov.petclinic.repository.UserRepository;
import com.bov.petclinic.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto create(User user) {
        if(user == null){
            throw new NullPointerException("User can't be 'null'");
        }
        Role role = roleRepository.findByName("ROLE_USER");
        User toSave = new User();
        toSave.setLogin(user.getLogin());
        toSave.setPassword(passwordEncoder.encode(user.getPassword()));
        toSave.setRole(role);
        try{
            userRepository.save(toSave);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        log.info("User [" + toSave.getLogin() + "] created ");
        return modelMapper.map(toSave,UserDto.class);
    }

    @Override
    public UserDto update(User user) {
        if(user == null){
            throw new NullPointerException("User can't be 'null'");
        }
        Role role = roleRepository.findByName("ROLE_USER");
        User toUpdate = userRepository.findById(user.getId()).orElseThrow(() -> new BadIdException("Bad id:" + user.getId()));
        toUpdate.setLogin(user.getLogin());
        toUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
        toUpdate.setRole(role);
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
}
