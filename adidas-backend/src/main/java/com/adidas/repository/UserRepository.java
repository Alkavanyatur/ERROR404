package com.adidas.repository;

import java.util.List;

import com.adidas.model.entity.User;

/**
 * @author Error404
 */
public interface UserRepository {

    User selectUserById(Long id);

    User selectUserByUsername(String username);

    List<User> selectAllUsers();

    Integer insertUser(User user);

    Integer updateUserOnPasswordById(User user);

    Integer deleteUserById(Long id);

}
