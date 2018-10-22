package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.models.user.User;
import com.wasp.landlordcommunication.repositories.base.UsersRepository;
import com.wasp.landlordcommunication.services.base.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Override
    public User createUser(User userToCreate) {
        return usersRepository.createUser(userToCreate);
    }

    @Override
    public User getUserByUserName(String userName) {
        return usersRepository.getUserByUserName(userName);
    }

    @Override
    public User getUserById(int userId) {
        return usersRepository.getUserById(userId);
    }

    @Override
    public boolean isUserNameAvailable(String name) {

        return usersRepository.isUserNameAvailable(name);
    }

    @Override
    public User updateUser(User userToUpdate, int userId) {
        return usersRepository.updateUser(userToUpdate, userId);
    }

    @Override
    public List<User> getUsersByType(String userType) {
        return usersRepository.getUsersByType(userType);
    }
}
