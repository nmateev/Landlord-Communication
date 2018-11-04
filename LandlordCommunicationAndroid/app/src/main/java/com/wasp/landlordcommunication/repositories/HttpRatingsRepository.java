package com.wasp.landlordcommunication.repositories;

import com.wasp.landlordcommunication.http.base.HttpRequester;
import com.wasp.landlordcommunication.models.Rating;
import com.wasp.landlordcommunication.parsers.base.JsonParser;
import com.wasp.landlordcommunication.repositories.base.RatingsRepository;
import com.wasp.landlordcommunication.utils.Constants;

import java.io.IOException;
import java.util.List;

public class HttpRatingsRepository implements RatingsRepository {

    private final HttpRequester mHttpRequester;
    private final String mServerUrl;
    private final JsonParser<Rating> mJsonParser;

    public HttpRatingsRepository(String serverUrl, HttpRequester httpRequester, JsonParser<Rating> jsonParser) {
        mServerUrl = serverUrl;
        mHttpRequester = httpRequester;
        mJsonParser = jsonParser;
    }


    @Override
    public List<Rating> getUserRatingById(int id) throws IOException {

        String url = mServerUrl + Constants.SLASH_STRING_VALUE + id;
        String itemsJson = mHttpRequester.get(url);

        return mJsonParser.fromJsonArray(itemsJson);

    }

    @Override
    public Rating checkIfUserAlreadyRatedByVoter(Rating rating) throws IOException {
        String requestBody = mJsonParser.toJson(rating);

        String postServerUrl = mServerUrl + Constants.RATINGS_CHECK_URL_SUFFIX;
        String responseBody = mHttpRequester.post(postServerUrl, requestBody);

        return mJsonParser.fromJson(responseBody);

    }

    @Override
    public Rating submitRating(Rating newRating) throws IOException {
        String requestBody = mJsonParser.toJson(newRating);
        String responseBody = mHttpRequester.post(mServerUrl, requestBody);

        return mJsonParser.fromJson(responseBody);

    }
}
