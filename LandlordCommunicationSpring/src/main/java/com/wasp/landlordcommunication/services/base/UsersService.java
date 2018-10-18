package com.wasp.landlordcommunication.services.base;

import com.wasp.landlordcommunication.models.User;

public interface UsersService {

    User createUser(User userToCreate);

    User getUserByUserName(String userName);
}
