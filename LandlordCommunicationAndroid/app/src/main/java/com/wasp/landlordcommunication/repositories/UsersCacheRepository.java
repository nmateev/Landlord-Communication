package com.wasp.landlordcommunication.repositories;

import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.repositories.base.CacheRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class UsersCacheRepository implements CacheRepository<User> {
    private final List<User> mUserList;

    @Inject
    public UsersCacheRepository() {
        mUserList = new ArrayList<>();
    }

    @Override
    public void cacheData(List<User> items) {
        mUserList.clear();
        mUserList.addAll(items);
    }

    @Override
    public List<User> getCachedData() {
        return mUserList;
    }
}
