package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.models.Rating;
import com.wasp.landlordcommunication.repositories.base.Repository;
import com.wasp.landlordcommunication.services.base.RatingsService;

import java.io.IOException;
import java.util.List;

public class HttpRatingsService implements RatingsService {


    private final Repository<Rating> mRatingsRepository;

    public HttpRatingsService(Repository<Rating> ratingsRepository) {
        mRatingsRepository = ratingsRepository;
    }

    @Override
    public double getUserRatingById(int id) throws IOException {
        List<Rating> userRatings = mRatingsRepository.getAllByParameter(String.valueOf(id));

        double rating = 0;
        int size = userRatings.size();
        if (size == 0) {
            return rating;
        } else {
            for (int i = 0; i < userRatings.size(); i++) {
                rating += userRatings.get(i).getRating();
            }
        }
        return rating / size;
    }
}
