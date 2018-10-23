package com.wasp.landlordcommunication.repositories.base;

import com.wasp.landlordcommunication.models.rating.Rating;
import com.wasp.landlordcommunication.models.rating.RatingDTO;

import java.util.List;

public interface RatingsRepository {

    Rating addRating(Rating newRating);

    List<Rating> getRatingsByUserId(int id);

    boolean isAlreadyRated(RatingDTO ratingDTO);
}
