package com.wasp.landlordcommunication.repositories;

import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.repositories.base.CacheRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PropertiesCacheRepository implements CacheRepository<Property> {
    private final List<Property> mPropertiesList;

    @Inject
    public PropertiesCacheRepository() {
        mPropertiesList = new ArrayList<>();
    }

    @Override
    public void cacheData(List<Property> items) {
        mPropertiesList.clear();
        mPropertiesList.addAll(items);
    }

    @Override
    public List<Property> getCachedData() {
        return mPropertiesList;
    }
}
