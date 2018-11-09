package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.models.user.User;
import com.wasp.landlordcommunication.repositories.base.UsersRepository;
import com.wasp.landlordcommunication.services.base.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User createUser(User userToCreate) {
        if (Objects.equals(userToCreate.getUserPassword(), null)) {
            //user choose to login with social media account, no password is stored on database
            return usersRepository.createUser(userToCreate);
        } else {
            String encodedPassword = passwordEncoder.encode(userToCreate.getUserPassword());
            userToCreate.setUserPassword(encodedPassword);
            return usersRepository.createUser(userToCreate);
        }
    }

    @Override
    public User getUserByUserName(String userName) {

        return usersRepository.getUserByUserName(userName);
    }

    @Override
    public User loginUser(User incomingUser) {

        if (Objects.equals(incomingUser, null)) {
            return null;
        } else {
            User user = usersRepository.getUserByUserName(incomingUser.getUserName());
            boolean isLoginSuccessful;

            try {
                isLoginSuccessful = passwordEncoder.matches(incomingUser.getUserPassword(), user.getUserPassword());
            } catch (NullPointerException npe) {
                return null;
            }
            if (!isLoginSuccessful) {
                return null;
            } else {
                return user;
            }
        }
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.getUserByUserName(username);

        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(), new HashSet<>());

        return userDetails;
    }
}
