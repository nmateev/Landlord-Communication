package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.models.rating.Rating;
import com.wasp.landlordcommunication.models.rating.RatingDTO;
import com.wasp.landlordcommunication.repositories.base.RatingsRepository;
import com.wasp.landlordcommunication.services.base.RatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingsServiceImpl implements RatingsService {

    private final RatingsRepository ratingsRepository;

    @Autowired
    public RatingsServiceImpl(RatingsRepository ratingsRepository) {
        this.ratingsRepository = ratingsRepository;
    }

    @Override
    public Rating addRating(Rating newRating) {
        return ratingsRepository.addRating(newRating);
    }

    @Override
    public List<Rating> getRatingsByUserId(int id) {
        return ratingsRepository.getRatingsByUserId(id);
    }

    @Override
    public boolean isAlreadyRated(RatingDTO ratingDTO) {
        return ratingsRepository.isAlreadyRated(ratingDTO);
    }
}
