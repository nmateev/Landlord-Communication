package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.repositories.base.PropertyRepository;
import com.wasp.landlordcommunication.services.base.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceImpl implements PropertyService {

    private PropertyRepository propertyRepository;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }


    @Override
    public Property getPropertyById(int id) {
        return propertyRepository.getPropertyById(id);
    }

    @Override
    public Property addNewProperty(Property newProperty) {

        return propertyRepository.addNewProperty(newProperty);
    }

    @Override
    public Property updateProperty(Property propertyToUpdate, int id) {

        return propertyRepository.updateProperty(propertyToUpdate, id);
    }
}
