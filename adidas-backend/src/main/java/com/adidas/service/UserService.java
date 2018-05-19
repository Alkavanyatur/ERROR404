package com.adidas.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.adidas.model.entity.User;

import java.util.Optional;

/**
 * @author Error404
 */
public interface UserService extends UserDetailsService {

    Optional<User> getUserById(Long id);

    boolean saveUser(User user);

    boolean modifyUserOnPasswordById(User user);

    boolean deleteUserById(Long id);

}
