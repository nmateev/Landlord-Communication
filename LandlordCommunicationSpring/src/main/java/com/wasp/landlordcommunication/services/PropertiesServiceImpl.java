package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.repositories.base.PropertiesRepository;
import com.wasp.landlordcommunication.services.base.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertiesServiceImpl implements PropertiesService {

    private final PropertiesRepository propertiesRepository;

    @Autowired
    public PropertiesServiceImpl(PropertiesRepository propertiesRepository) {
        this.propertiesRepository = propertiesRepository;
    }


    @Override
    public Property getPropertyById(int id) {
        return propertiesRepository.getPropertyById(id);
    }

    @Override
    public Property addNewProperty(Property newProperty) {

        return propertiesRepository.addNewProperty(newProperty);
    }

    @Override
    public Property updateProperty(Property propertyToUpdate, int id) {

        return propertiesRepository.updateProperty(propertyToUpdate, id);
    }
}
