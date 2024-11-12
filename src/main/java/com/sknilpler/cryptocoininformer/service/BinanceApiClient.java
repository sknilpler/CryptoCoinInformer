package com.sknilpler.cryptocoininformer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.sknilpler.cryptocoininformer.enums.ExchangeApiUrl24H;
import com.sknilpler.cryptocoininformer.model.Ticker;
import com.sknilpler.cryptocoininformer.util.ApiClient;
import com.sknilpler.cryptocoininformer.util.FormattingSymbol;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BinanceApiClient implements TickerService {

    private final ApiClient apiClient;

    // Внедрение через конструктор
    public BinanceApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public JsonNode get24HourData() throws Exception {
        return apiClient.getData(ExchangeApiUrl24H.BINANCE_24H.getUrl24H());
    }

    @Override
    public List<Ticker> parseData(JsonNode data) {
        List<Ticker> parsedData = new ArrayList<>();
        for (JsonNode coin : data) {
            Ticker crypto = new Ticker();
            crypto.setSymbol(FormattingSymbol.formatSymbol(coin.get("symbol").asText()));
            crypto.setLastPrice(coin.get("lastPrice").asDouble());
            crypto.setPriceChangePercent(coin.get("priceChangePercent").asDouble());
            crypto.setVolume(coin.get("volume").asDouble());
            crypto.setHighPrice(coin.get("highPrice").asDouble());
            crypto.setLowPrice(coin.get("lowPrice").asDouble());
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