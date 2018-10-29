package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.repositories.base.Repository;
import com.wasp.landlordcommunication.services.base.PropertiesService;
import com.wasp.landlordcommunication.utils.Constants;

import java.io.IOException;
import java.util.List;

public class HttpPropertiesService implements PropertiesService {

    private final Repository<Property> mPropertiesRepository;

    public HttpPropertiesService(Repository<Property> propertiesRepository) {
        mPropertiesRepository = propertiesRepository;
    }

    @Override
    public List<Property> getUsersPropertiesByIdAndType(int userId, String userType) throws IOException {
        String parameter = userType.toLowerCase() + Constants.SLASH_STRING_VALUE + userId;

        return mPropertiesRepository.getAllByParameter(parameter);
    }

    @Override
    public Property getPropertyById(int propertyId) throws IOException {
        return mPropertiesRepository.getById(propertyId);
    }
}
