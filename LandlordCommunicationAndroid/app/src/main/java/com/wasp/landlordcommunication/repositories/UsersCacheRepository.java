package com.wasp.landlordcommunication.repositories;

import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.repositories.base.CacheRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class UsersCacheRepository implements CacheRepository<User> {
    private final List<User> userList;

    @Inject
    public UsersCacheRepository() {
        userList = new ArrayList<>();
    }

    @Override
    public void cacheData(List<User> items) {
        userList.clear();
        userList.addAll(items);
    }

    @Override
    public List<User> getCachedData() {
        return userList;
    }
}
