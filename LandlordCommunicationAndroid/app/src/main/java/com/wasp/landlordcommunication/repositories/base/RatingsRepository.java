package com.wasp.landlordcommunication.repositories.base;

import com.wasp.landlordcommunication.models.Rating;

import java.io.IOException;
import java.util.List;

public interface RatingsRepository {


    List<Rating> getUserRatingById(int id) throws IOException;

    Rating checkIfUserAlreadyRatedByVoter(Rating rating) throws IOException;

    Rating submitRating(Rating newRating) throws IOException;
}
