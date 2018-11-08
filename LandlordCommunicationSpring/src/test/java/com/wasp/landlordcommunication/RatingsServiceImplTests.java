package com.wasp.landlordcommunication;

import com.wasp.landlordcommunication.models.rating.Rating;
import com.wasp.landlordcommunication.models.rating.RatingDTO;
import com.wasp.landlordcommunication.repositories.base.RatingsRepository;
import com.wasp.landlordcommunication.services.RatingsServiceImpl;
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
public class RatingsServiceImplTests {
    @Mock
    RatingsRepository mockRepository;

    @InjectMocks
    RatingsServiceImpl service;

    List<Rating> defaultTestInput = Arrays.asList(
            new Rating(
                    1,
                    1,
                    1.1),
            new Rating(
                    2,
                    2,
                    2.2),
            new Rating(
                    3,
                    3,
                 3.3));

    @Test
    public void addRating_Should_ReturnNewRating(){
        // Arrange
        Rating newRating = new Rating();
        Mockito.when(mockRepository.addRating(newRating))
                .thenReturn(newRating);

        // Act
        Rating rating = service.addRating(newRating);

        // Assert
        Assert.assertEquals(rating, newRating);
    }

    @Test
    public void getRatingsByUserId_Should_ReturnMatchingRatings_WhenMatchExist(){
        // Arrange
        Mockito.when(mockRepository.getRatingsByUserId(1))
                .thenReturn(defaultTestInput);

        // Act
        List<Rating> result = service.getRatingsByUserId(1);

        // Assert
        Assert.assertEquals(result, defaultTestInput);
    }

    @Test
    public void isAlreadyRated_Should_ReturnMatchingRating_WhenMatchExist(){
        // Arrange
        RatingDTO ratingDTO=new RatingDTO();
        Mockito.when(mockRepository.isAlreadyRated(ratingDTO))
                .thenReturn(defaultTestInput.get(0));

        // Act
        Rating result = service.isAlreadyRated(ratingDTO);

        // Assert
        Assert.assertEquals(result, defaultTestInput.get(0));
    }

}
