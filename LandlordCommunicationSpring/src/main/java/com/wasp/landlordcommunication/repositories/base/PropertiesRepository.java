package com.wasp.landlordcommunication.repositories.base;

import com.wasp.landlordcommunication.models.Property;

public interface PropertiesRepository {

    Property getPropertyById(int id);

    Property addNewProperty(Property newProperty);

    Property updateProperty(Property propertyToUpdate, int id);
}
