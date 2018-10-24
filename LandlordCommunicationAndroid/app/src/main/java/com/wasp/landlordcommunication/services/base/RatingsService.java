package com.wasp.landlordcommunication.services.base;

import com.wasp.landlordcommunication.models.Rating;

import java.io.IOException;
import java.util.List;

public interface RatingsService {

    List<Rating> getUserRatingById(int id) throws IOException;
}
