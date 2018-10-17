package com.wasp.landlordcommunication.services.base;

import com.wasp.landlordcommunication.models.properties.Property;

import java.util.List;

public interface PropertyService {
    List<Property> getAllProperties();
    Property getPropertyById(int id);
}
