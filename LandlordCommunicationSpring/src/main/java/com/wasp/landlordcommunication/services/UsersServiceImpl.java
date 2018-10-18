package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.repositories.base.UsersRepository;
import com.wasp.landlordcommunication.services.base.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
