package com.wasp.landlordcommunication.repositories.base;

import com.wasp.landlordcommunication.models.user.User;

import java.util.List;

public interface UsersRepository {


    User createUser(User userToCreate);

    User getUserByUserName(String userName);

    User getUserById(int userId);

    boolean isUserNameAvailable(String name);

    User updateUser(User userToUpdate, int userId);

    List<User> getUsersByType(String userType);
}
