package com.sknilpler.cryptocoininformer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class BybitApiClient {
    private static final String URL_24H = "https://api.bybit.com/v2/public/tickers";

    public static JsonNode get24HourData() throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(URL_24H);
        CloseableHttpResponse response = client.execute(request);

        String responseBody = EntityUtils.toString(response.getEntity());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(responseBody);
    }
}

