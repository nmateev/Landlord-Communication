package com.wasp.landlordcommunication;

import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.repositories.base.PropertiesRepository;
import com.wasp.landlordcommunication.services.PropertiesServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PropertiesServiceImplTests {
    @Mock
   PropertiesRepository mockRepository;

    @InjectMocks
    PropertiesServiceImpl service;

    List<Property> defaultTestInput = Arrays.asList(
            new Property(
                    1,
                    1,
                    1000.00,
                    10,
                   true,
                    "Address1",
                    "Picture1",
            "Desc1"),
            new Property(
                    2,
                    2,
                    2000.00,
                    20,
                    true,
                    "Address2",
                    "Picture2",
            "Desc2"),
            new Property(
                    3,
                    3,
                    3000.00,
                    30,
                    false,
                    "Address3",
                    "Picture3",
                    "Desc3"));



    @Test
    public void getPropertyById_Should_ReturnMatchingProperty_WhenMatchExist() {
        // Arrange
        Mockito.when(mockRepository.getPropertyById(1))
                .thenReturn(defaultTestInput.get(0));

        // Act
        Property result = service.getPropertyById(1);

        // Assert
        Assert.assertEquals(result, defaultTestInput.get(0));
    }

    @Test
    public void getPropertyByTenantId_Should_ReturnMatchingProperty_WhenMatchExist() {
        // Arrange
        Mockito.when(mockRepository.getPropertiesByTenant(2))
                .thenReturn(defaultTestInput);

        // Act
        List<Property> result = service.getPropertiesByTenant(2);

        // Assert
        Assert.assertEquals(result, defaultTestInput);
    }

    @Test
    public void getPropertyByLandlordId_Should_ReturnMatchingProperty_WhenMatchExist() {
        // Arrange
        Mockito.when(mockRepository.getPropertiesByLandlord(3))
                .thenReturn(defaultTestInput);

        // Act
        List<Property> result = service.getPropertiesByLandlord(3);

        // Assert
        Assert.assertEquals(result, defaultTestInput);
    }

    @Test
    public void addNewProperty_Should_ReturnNewProperty(){
        // Arrange
       Property newProperty = new Property();
        Mockito.when(mockRepository.addNewProperty(newProperty))
                .thenReturn(newProperty);

        // Act
        Property property = service.addNewProperty(newProperty);

        // Assert
        Assert.assertEquals(property, newProperty);
    }

    @Test
    public void updateNewProperty_Should_ReturnUpdatedProperty(){
        // Arrange
        Property newProperty = new Property();
        Mockito.when(mockRepository.updateProperty(newProperty,3))
                .thenReturn(newProperty);

        // Act
        Property property = service.updateProperty(newProperty,3);

        // Assert
        Assert.assertEquals(property, newProperty);
    }

}
