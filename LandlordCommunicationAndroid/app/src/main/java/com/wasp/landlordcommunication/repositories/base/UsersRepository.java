package com.wasp.landlordcommunication.repositories.base;

import com.wasp.landlordcommunication.models.User;

import java.io.IOException;
import java.util.List;

public interface UsersRepository {

    User getUserByUserName(String name) throws IOException;

    User loginUser(User user) throws IOException;

    User createUser(User userToCreate) throws IOException;

    User updateUser(User user, int userId) throws IOException;

    List<User> getAllUsersByType(String type) throws IOException;

}
