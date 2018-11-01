package com.wasp.landlordcommunication.services.base;

import com.wasp.landlordcommunication.models.Property;

import java.io.IOException;
import java.util.List;

public interface PropertiesService {

    List<Property> getUsersPropertiesByIdAndType(int userId, String userType) throws IOException;

    Property getPropertyById(int propertyId) throws IOException;

    Property updateProperty(Property property, int propertyId) throws IOException;

    List<Property> getPropertiesByIdAndTypeOnlyPaidStatus(int userId, String userType);

    List<Property> getPropertiesByIdAndTypeOnlyNotPaidStatus(int userId, String userType);

    List<Property> getPropertiesByIdAndTypeSortAscending(int userId, String userType);

    List<Property> getPropertiesByIdAndTypeSortDescending(int userId, String userType);
}
