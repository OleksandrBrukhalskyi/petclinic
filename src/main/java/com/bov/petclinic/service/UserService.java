package com.bov.petclinic.service;

import com.bov.petclinic.security.RegisterRequest;
import com.bov.petclinic.dto.user.UserDto;
import com.bov.petclinic.entity.User;

import java.util.List;

public interface UserService {
    void create(RegisterRequest registerRequest);
    UserDto update(UserDto userDto);
    UserDto getById(long id);
    void delete(long id);
    List<UserDto> getAll();
    User findByLogin(String login);
    User findByLoginAndPassword(String login,String password);
    Boolean existsByLogin(String login);
}
