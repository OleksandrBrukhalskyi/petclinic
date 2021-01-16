package com.bov.petclinic.service;

import com.bov.petclinic.dto.UserDto;
import com.bov.petclinic.entity.User;

import java.util.List;

public interface UserService {
    UserDto create(User user);
    UserDto update(User user);
    UserDto getById(long id);
    void delete(long id);
    List<UserDto> getAll();
}
