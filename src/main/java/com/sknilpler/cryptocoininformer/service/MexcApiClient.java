package com.sknilpler.cryptocoininformer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sknilpler.cryptocoininformer.enums.ExchangeApiUrl24H;
import com.sknilpler.cryptocoininformer.model.Ticker;
import com.sknilpler.cryptocoininformer.util.ApiClient;
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
public class MexcApiClient implements TickerService {

    private final ApiClient apiClient;

    public MexcApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public JsonNode get24HourData() throws Exception {
        return apiClient.getData(ExchangeApiUrl24H.MEXC_24H.getUrl24H());
    }

    // Метод для приведения данных в единый формат
    @Override
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

    @Override
    public List<Ticker> getParsing24HoursData() {
        try {
            return parseData(get24HourData());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
