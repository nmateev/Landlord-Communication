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
    public List<Rating> getUserRatingById(int id) throws IOException {
        return mRatingsRepository.getAllByParameter(id);
    }
}
