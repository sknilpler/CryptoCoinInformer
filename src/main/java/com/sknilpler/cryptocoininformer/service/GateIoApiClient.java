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
public class GateIoApiClient implements TickerService{

    private final ApiClient apiClient;

    public GateIoApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public JsonNode get24HourData() throws Exception {
        return apiClient.getData(ExchangeApiUrl24H.GATEIO_24H.getUrl24H());
    }

    // Метод для приведения данных в единый формат
    @Override
    public List<Ticker> parseData(JsonNode data) {
        List<Ticker> parsedData = new ArrayList<>();
        for (JsonNode coin : data) {
            Ticker crypto = new Ticker();
            crypto.setSymbol(FormattingSymbol.formatSymbol(coin.get("currency_pair").asText()));
            crypto.setLastPrice(coin.get("last").asDouble());
            crypto.setPriceChangePercent(Double.parseDouble(coin.get("change_percentage").asText()));
            crypto.setVolume(coin.get("base_volume").asDouble());
            crypto.setHighPrice(coin.get("high_24h").asDouble());
            crypto.setLowPrice(coin.get("low_24h").asDouble());
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
