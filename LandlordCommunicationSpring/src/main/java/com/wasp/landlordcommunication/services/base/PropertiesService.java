package com.wasp.landlordcommunication.services.base;

import com.wasp.landlordcommunication.models.Property;

import java.util.List;

public interface PropertiesService {

    Property getPropertyById(int id);

    List<Property> getPropertiesByTenant(int tenantId);

    List<Property> getPropertiesByLandlord(int landlordId);

    Property addNewProperty(Property newProperty);

    Property updateProperty(Property propertyToUpdate, int id);
}
