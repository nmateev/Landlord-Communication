package com.wasp.landlordcommunication.repositories.base;

import com.wasp.landlordcommunication.models.properties.Property;

import java.util.List;

public interface PropertyRepository {
    List<Property> getAllProperties();
    Property getPropertyById(int id);
}
