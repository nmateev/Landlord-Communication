package com.wasp.landlordcommunication.services.base;

import com.wasp.landlordcommunication.models.Property;

import java.io.IOException;
import java.util.List;

public interface PropertiesService {

    List<Property> getUsersPropertiesByIdAndType(int userId, String userType) throws IOException;
}
