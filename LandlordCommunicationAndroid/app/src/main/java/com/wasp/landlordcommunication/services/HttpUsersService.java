package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.repositories.base.CacheRepository;
import com.wasp.landlordcommunication.repositories.base.UsersRepository;
import com.wasp.landlordcommunication.services.base.UsersService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpUsersService implements UsersService {

    private final UsersRepository mUsersRepository;
    private final CacheRepository<User> mUsersCacheRepository;

    public HttpUsersService(UsersRepository userRepository, CacheRepository<User> usersCacheRepository) {
        mUsersRepository = userRepository;
        mUsersCacheRepository = usersCacheRepository;
    }

    @Override
    public User getUserByUserName(String name) throws IOException {
        return mUsersRepository.getUserByUserName(name);
    }

    @Override
    public User loginUser(User user) throws IOException {
        return mUsersRepository.loginUser(user);
    }

    @Override
    public User createUser(User userToCreate) throws IOException {
        return mUsersRepository.createUser(userToCreate);
    }

    @Override
    public User updateUser(User user, int userId) throws IOException {
        return mUsersRepository.updateUser(user, userId);
    }

    @Override
    public List<User> getAllUsersByType(String type, int excludedIdFromResult) throws IOException {
        List<User> result = mUsersRepository.getAllUsersByType(type);
        //TODO uncomment when api  level is set to 24
       /* result
                .stream()
                .filter(user -> user.getUserId() != excludedIdFromResult)
                .collect(Collectors.toList());*/

        for (User user : result) {
            if (user.getUserId() == excludedIdFromResult) {
                result.remove(user);
            }
        }
        mUsersCacheRepository.cacheData(result);
        return result;
    }

    @Override
    public List<User> getFilteredUsersByName(String pattern) {
        String lowerCasePattern = pattern.toLowerCase();
        List<User> cachedLandlordsList = mUsersCacheRepository.getCachedData();

        List<User> filteredResult = new ArrayList<>();
        for (User user : cachedLandlordsList) {
            if (user.getFirstName().toLowerCase().contains(lowerCasePattern) || user.getLastName().toLowerCase().contains(lowerCasePattern)) {
                filteredResult.add(user);
            }
        }
        return filteredResult;
    }
}

