package com.qp.service;

import com.qp.entity.UserEntity;

public interface UserService {
	UserEntity addUser(UserEntity user);
    UserEntity getUserById(Long id);
}
