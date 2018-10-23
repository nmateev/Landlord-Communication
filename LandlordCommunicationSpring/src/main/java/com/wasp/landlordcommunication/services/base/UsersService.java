package com.wasp.landlordcommunication.services.base;

import com.wasp.landlordcommunication.models.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsersService extends UserDetailsService {

    User createUser(User userToCreate);

    User getUserByUserName(String userName);

    User loginUser(User user);

    User getUserById(int userId);

    boolean isUserNameAvailable(String name);

    User updateUser(User userToUpdate, int userId);

    List<User> getUsersByType(String userType);
}
