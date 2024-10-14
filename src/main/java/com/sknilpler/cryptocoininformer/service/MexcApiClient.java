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
public class MexcApiClient {
    private static final String MEXC_API_URL = "https://www.mexc.com/open/api/v2/market/ticker";

    public JsonNode get24HourData() throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(MEXC_API_URL);
        CloseableHttpResponse response = client.execute(request);

        String responseBody = EntityUtils.toString(response.getEntity());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(responseBody);
    }

    // Метод для приведения данных в единый формат
    public List<Ticker> parseData(JsonNode data) {
        List<Ticker> parsedData = new ArrayList<>();
        for (JsonNode coin : data.get("data")) {
            Ticker crypto = new Ticker();
            crypto.setSymbol(FormattingSymbol.formatSymbol(coin.get("symbol").asText()));
            crypto.setLastPrice(coin.get("last").asDouble());
            crypto.setPriceChangePercent(Double.parseDouble(coin.get("change_rate").asText()) * 100);
            crypto.setVolume(coin.get("volume").asDouble());
            crypto.setHighPrice(coin.get("high").asDouble());
            crypto.setLowPrice(coin.get("low").asDouble());
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
