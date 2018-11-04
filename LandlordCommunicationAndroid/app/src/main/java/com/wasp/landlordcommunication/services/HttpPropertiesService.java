package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.repositories.base.CacheRepository;
import com.wasp.landlordcommunication.repositories.base.PropertiesRepository;
import com.wasp.landlordcommunication.services.base.PropertiesService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HttpPropertiesService implements PropertiesService {

    private final PropertiesRepository mPropertiesRepository;
    private final CacheRepository<Property> mPropertiesCacheRepository;

    public HttpPropertiesService(PropertiesRepository propertiesRepository, CacheRepository<Property> propertiesCacheRepository) {
        mPropertiesRepository = propertiesRepository;
        mPropertiesCacheRepository = propertiesCacheRepository;
    }

    @Override
    public List<Property> getUsersPropertiesByIdAndType(int userId, String userType) throws IOException {
        List<Property> result = mPropertiesRepository.getUsersPropertiesByIdAndType(userId, userType);

        mPropertiesCacheRepository.cacheData(result);
        return result;
    }

    @Override
    public Property getPropertyById(int propertyId) throws IOException {
        return mPropertiesRepository.getPropertyById(propertyId);
    }

    @Override
    public Property updateProperty(Property property, int propertyId) throws IOException {
        return mPropertiesRepository.updateProperty(property, propertyId);
    }

    @Override
    public List<Property> getPropertiesByIdAndTypeOnlyPaidStatus(int userId, String userType) {

        List<Property> properties = mPropertiesCacheRepository.getCachedData();
        List<Property> result = new ArrayList<>();

        for (Property property : properties) {
            if (property.getRentPaid()) {
                result.add(property);
            }
        }
        return result;
    }

    @Override
    public List<Property> getPropertiesByIdAndTypeOnlyNotPaidStatus(int userId, String userType) {
        List<Property> properties = mPropertiesCacheRepository.getCachedData();
        List<Property> result = new ArrayList<>();

        for (Property property : properties) {
            if (!property.getRentPaid()) {
                result.add(property);
            }
        }
        return result;
    }

    @Override
    public List<Property> getPropertiesByIdAndTypeSortAscending(int userId, String userType) {
        List<Property> properties = mPropertiesCacheRepository.getCachedData();
        Collections.sort(properties, (firstProperty, secondProperty) -> Double.compare(firstProperty.getRentPrice(), secondProperty.getRentPrice()));

        return properties;
    }

    @Override
    public List<Property> getPropertiesByIdAndTypeSortDescending(int userId, String userType) {
        List<Property> properties = mPropertiesCacheRepository.getCachedData();
        Collections.sort(properties, (secondProperty, firstProperty) -> Double.compare(firstProperty.getRentPrice(), secondProperty.getRentPrice()));

        return properties;
    }
}
