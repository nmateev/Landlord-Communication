package com.wasp.landlordcommunication.repositories.base;

import com.wasp.landlordcommunication.models.User;

public interface UsersRepository {


    User createUser(User userToCreate);

    User getUserByUserName(String userName);
}
