package com.wasp.landlordcommunication.services.base;

import com.wasp.landlordcommunication.models.Rating;

import java.io.IOException;

public interface RatingsService {

    double getUserRatingById(int id) throws IOException;

    Rating checkIfUserAlreadyRatedByVoter(Rating rating) throws IOException;

    Rating submitRating(Rating newRating) throws IOException;
}
