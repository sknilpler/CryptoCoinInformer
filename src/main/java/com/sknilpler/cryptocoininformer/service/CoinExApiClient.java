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
public class CoinExApiClient implements TickerService {
    private static final String URL_24H = "https://api.coinex.com/v1/market/ticker/all";

    private final ApiClient apiClient;


    public CoinExApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public JsonNode get24HourData() throws Exception {
        return apiClient.getData(ExchangeApiUrl24H.COINEX_24H.getUrl24H());
    }

    // Метод для приведения данных CoinEx в единый формат
    @Override
    public List<Ticker> parseData(JsonNode data) {
        List<Ticker> parsedData = new ArrayList<>();

        // Проверяем, что данные существуют и содержат раздел "ticker"
        if (data != null && data.has("data") && data.get("data").has("ticker")) {
            JsonNode tickers = data.get("data").get("ticker");

            // Проходим по каждому символу и его данным
            tickers.fields().forEachRemaining(entry -> {
                String symbol = FormattingSymbol.formatSymbol(entry.getKey());  // Символ (ключ)
                JsonNode ticker = entry.getValue();  // Данные по символу

                // Создаем объект Ticker и заполняем его данными
                Ticker crypto = new Ticker();
                crypto.setSymbol(symbol);  // Символ берется как ключ

                double lastPrice = ticker.get("last").asDouble();
                double openPrice = ticker.get("open").asDouble();

                // Вычисление процента изменения цены
                double priceChangePercent = ((lastPrice - openPrice) / openPrice) * 100;

                crypto.setLastPrice(lastPrice);
                crypto.setPriceChangePercent(priceChangePercent);
                crypto.setVolume(ticker.get("vol").asDouble());
                crypto.setHighPrice(ticker.get("high").asDouble());
                crypto.setLowPrice(ticker.get("low").asDouble());

                parsedData.add(crypto);  // Добавляем объект в список
            });
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

