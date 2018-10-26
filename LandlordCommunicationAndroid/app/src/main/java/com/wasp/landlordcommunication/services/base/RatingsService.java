package com.wasp.landlordcommunication.services.base;

import java.io.IOException;

public interface RatingsService {

    double getUserRatingById(int id) throws IOException;
}
