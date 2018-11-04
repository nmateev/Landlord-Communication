package com.wasp.landlordcommunication.repositories;

import com.wasp.landlordcommunication.http.base.HttpRequester;
import com.wasp.landlordcommunication.models.Payment;
import com.wasp.landlordcommunication.parsers.base.JsonParser;
import com.wasp.landlordcommunication.repositories.base.PaymentsRepository;
import com.wasp.landlordcommunication.utils.Constants;

import java.io.IOException;
import java.util.List;

public class HttpPaymentsRepository implements PaymentsRepository {

    private final HttpRequester mHttpRequester;
    private final String mServerUrl;
    private final JsonParser<Payment> mJsonParser;

    public HttpPaymentsRepository(String serverUrl, HttpRequester httpRequester, JsonParser<Payment> jsonParser) {
        mServerUrl = serverUrl;
        mHttpRequester = httpRequester;
        mJsonParser = jsonParser;
    }


    @Override
    public List<Payment> getAllPaymentsById(String userType, int id) throws IOException {

        String url = new StringBuilder()
                .append(mServerUrl)
                .append(Constants.SLASH_STRING_VALUE)
                .append(userType.toLowerCase())
                .append(Constants.SLASH_STRING_VALUE)
                .append(id)
                .toString();

        String itemsJson = mHttpRequester.get(url);

        return mJsonParser.fromJsonArray(itemsJson);

    }

    @Override
    public Payment makePayment(Payment payment) throws IOException {

        String requestBody = mJsonParser.toJson(payment);
        String responseBody = mHttpRequester.post(mServerUrl, requestBody);

        return mJsonParser.fromJson(responseBody);
    }
}
