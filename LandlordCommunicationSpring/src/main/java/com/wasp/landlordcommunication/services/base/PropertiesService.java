package com.wasp.landlordcommunication.services.base;

import com.wasp.landlordcommunication.models.Property;

public interface PropertiesService {

    Property getPropertyById(int id);

    Property addNewProperty(Property newProperty);

    Property updateProperty(Property propertyToUpdate, int id);
}
