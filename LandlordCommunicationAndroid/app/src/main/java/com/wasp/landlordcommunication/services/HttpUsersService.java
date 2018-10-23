package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.repositories.base.Repository;
import com.wasp.landlordcommunication.services.base.UsersService;

import java.io.IOException;

public class HttpUsersService implements UsersService {

    private final Repository<User> mUsersRepository;

    public HttpUsersService(Repository<User> userRepository) {
        mUsersRepository = userRepository;
    }

    @Override
    public User getUserByUserName(String name) throws IOException {
        return mUsersRepository.getByParameter(name);
    }

    @Override
    public User loginUser(User user) throws IOException {
        return mUsersRepository.post(user);
    }
}
