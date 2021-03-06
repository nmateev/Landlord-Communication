package com.wasp.landlordcommunication.controllers;

import com.wasp.landlordcommunication.models.rating.Rating;
import com.wasp.landlordcommunication.models.rating.RatingDTO;
import com.wasp.landlordcommunication.services.base.RatingsService;
import com.wasp.landlordcommunication.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Constants.RATINGS_ROOT_MAPPING)
public class RatingsApiController {
    private final RatingsService ratingsService;

    @Autowired
    public RatingsApiController(RatingsService ratingsService) {
        this.ratingsService = ratingsService;
    }


    @RequestMapping(method = RequestMethod.POST)
    public Rating addRating(@RequestBody @Valid Rating newRating) {
        return ratingsService.addRating(newRating);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public List<Rating> getRatingsByUserId(@PathVariable int userId) {
        return ratingsService.getRatingsByUserId(userId);
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public Rating isAlreadyRated(@RequestBody RatingDTO ratingDTO) {
        return ratingsService.isAlreadyRated(ratingDTO);
    }
}
