package com.wasp.landlordcommunication.controllers;

import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.services.base.PropertyService;
import com.wasp.landlordcommunication.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.PROPERTY_ROOT_MAPPING)
public class PropertiesApiController {

    private PropertyService propertyService;

    @Autowired
    public PropertiesApiController(PropertyService propertyService) {

        this.propertyService = propertyService;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Property getPropertyById(@PathVariable int id) {
        return propertyService.getPropertyById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Property addNewProperty(@RequestBody Property newProperty) {
        return propertyService.addNewProperty(newProperty);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Property updateProperty(@RequestBody Property propertyToUpdate, int id) {

        return propertyService.updateProperty(propertyToUpdate, id);
    }

}
