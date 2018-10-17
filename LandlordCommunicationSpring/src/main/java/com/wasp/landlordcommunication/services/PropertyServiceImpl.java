package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.models.properties.Property;
import com.wasp.landlordcommunication.repositories.base.PropertyRepository;
import com.wasp.landlordcommunication.services.base.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PropertyServiceImpl implements PropertyService {
    private PropertyRepository repository;

    @Autowired
    public PropertyServiceImpl(PropertyRepository repository){
        this.repository=repository;
    }


    @Override
    public List<Property> getAllProperties() {
        return repository.getAllProperties();
    }

    @Override
    public Property getPropertyById(int id) {
        return repository.getPropertyById(id);
    }
}
