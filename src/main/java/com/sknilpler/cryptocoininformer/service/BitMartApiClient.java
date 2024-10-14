package com.sknilpler.cryptocoininformer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sknilpler.cryptocoininformer.model.Ticker;
import com.sknilpler.cryptocoininformer.util.FormattingSymbol;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BitMartApiClient {
    private static final String BITMART_API_URL = "https://api-cloud.bitmart.com/spot/v1/ticker";

    public JsonNode get24HourData() throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(BITMART_API_URL);
        CloseableHttpResponse response = client.execute(request);

        String responseBody = EntityUtils.toString(response.getEntity());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(responseBody);
    }

    // Метод для приведения данных в единый формат
    public List<Ticker> parseData(JsonNode data) {
        List<Ticker> parsedData = new ArrayList<>();
        for (JsonNode coin : data.get("data").get("tickers")) {
            Ticker crypto = new Ticker();
            crypto.setSymbol(FormattingSymbol.formatSymbol(coin.get("symbol").asText()));
            crypto.setLastPrice(coin.get("last_price").asDouble());
            crypto.setPriceChangePercent(Double.parseDouble(coin.get("fluctuation").asText()));
            crypto.setVolume(coin.get("base_volume_24h").asDouble());
            crypto.setHighPrice(coin.get("high_24h").asDouble());
            crypto.setLowPrice(coin.get("low_24h").asDouble());
            parsedData.add(crypto);
        }
        return parsedData;
    }

    public List<Ticker> getParsing24HoursData() {
        try {
            return parseData(get24HourData());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}

