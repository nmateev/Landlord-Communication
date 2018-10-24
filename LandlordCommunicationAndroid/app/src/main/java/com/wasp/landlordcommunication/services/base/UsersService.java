package com.wasp.landlordcommunication.services.base;

import com.wasp.landlordcommunication.models.User;

import java.io.IOException;

public interface UsersService {

    User getUserByUserName(String name) throws IOException;

    User loginUser(User user) throws IOException;

    User createUser(User userToCreate) throws IOException;
}
