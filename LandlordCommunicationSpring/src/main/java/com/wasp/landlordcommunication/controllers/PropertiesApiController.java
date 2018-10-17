package com.wasp.landlordcommunication.controllers;

import com.wasp.landlordcommunication.models.properties.Property;
import com.wasp.landlordcommunication.services.base.PropertyService;
import com.wasp.landlordcommunication.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Constants.PROPERTY_ROOT_MAPPING)
public class PropertiesApiController {
    private PropertyService propertyService;

    @Autowired
    public PropertiesApiController(PropertyService service){
        propertyService=service;
    }

    @GetMapping
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    @GetMapping("/{id}")
    public Property getPropertyById(@PathVariable int id) {
        return propertyService.getPropertyById(id);
    }



}
