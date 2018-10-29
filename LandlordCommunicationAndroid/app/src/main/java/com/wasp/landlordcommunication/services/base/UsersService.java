package com.wasp.landlordcommunication.services.base;

import com.wasp.landlordcommunication.models.User;

import java.io.IOException;
import java.util.List;

public interface UsersService {

    User getUserByUserName(String name) throws IOException;

    User loginUser(User user) throws IOException;

    User createUser(User userToCreate) throws IOException;

    User updateUser(User user, int userId) throws IOException;

    List<User> getAllUsersByType(String type, int excludedIdFromResult) throws IOException;

    List<User> getFilteredUsersByName(String pattern);

}
