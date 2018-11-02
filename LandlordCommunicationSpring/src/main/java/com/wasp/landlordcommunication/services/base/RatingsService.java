package com.wasp.landlordcommunication.services.base;

import com.wasp.landlordcommunication.models.rating.Rating;
import com.wasp.landlordcommunication.models.rating.RatingDTO;

import java.util.List;

public interface RatingsService {

    Rating addRating(Rating newRating);

    List<Rating> getRatingsByUserId(int id);

    Rating isAlreadyRated(RatingDTO ratingDTO);
}
