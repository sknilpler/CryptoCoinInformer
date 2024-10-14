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
public class CoinExApiClient {
    private static final String COINEX_API_URL = "https://api.coinex.com/v1/market/ticker/all";

    public JsonNode get24HourData() throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(COINEX_API_URL);
        CloseableHttpResponse response = client.execute(request);

        String responseBody = EntityUtils.toString(response.getEntity());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(responseBody);
    }

    // Метод для приведения данных CoinEx в единый формат
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

    public List<Ticker> getParsing24HoursData() {
        try {
            return parseData(get24HourData());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


}

