package com.wasp.landlordcommunication.controllers;

import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.services.base.PropertiesService;
import com.wasp.landlordcommunication.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.PROPERTIES_ROOT_MAPPING)
public class PropertiesApiController {

    private final PropertiesService propertiesService;

    @Autowired
    public PropertiesApiController(PropertiesService propertiesService) {

        this.propertiesService = propertiesService;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Property getPropertyById(@PathVariable int id) {
        return propertiesService.getPropertyById(id);
    }

    @RequestMapping(value = "/tenant/{tenantId}", method = RequestMethod.GET)
    List<Property> getPropertiesByTenant(@PathVariable int tenantId) {
        return propertiesService.getPropertiesByTenant(tenantId);
    }

    @RequestMapping(value = "/landlord/{landlordId}", method = RequestMethod.GET)
    List<Property> getPropertiesByLandlord(@PathVariable int landlordId) {
        return propertiesService.getPropertiesByLandlord(landlordId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Property addNewProperty(@RequestBody Property newProperty) {
        return propertiesService.addNewProperty(newProperty);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Property updateProperty(@RequestBody Property propertyToUpdate, @PathVariable int id) {

        return propertiesService.updateProperty(propertyToUpdate, id);
    }

}
