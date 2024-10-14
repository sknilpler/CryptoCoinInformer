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
public class OkxApiClient {
    private static final String OKX_API_URL = "https://www.okx.com/api/v5/market/tickers?instType=SPOT";

    public JsonNode get24HourData() throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(OKX_API_URL);
        CloseableHttpResponse response = client.execute(request);

        String responseBody = EntityUtils.toString(response.getEntity());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(responseBody);
    }

    // Метод для приведения данных OKX в единый формат
    public List<Ticker> parseData(JsonNode data) {
        List<Ticker> parsedData = new ArrayList<>();

        // Проверяем, что данные существуют и содержат раздел "data"
        if (data != null && data.has("data")) {
            JsonNode tickers = data.get("data");

            // Проходим по каждому тикеру в массиве
            for (JsonNode ticker : tickers) {
                // Создаем объект Ticker и заполняем его данными
                Ticker crypto = new Ticker();
                crypto.setSymbol(FormattingSymbol.formatSymbol(ticker.get("instId").asText()));  // Символ монеты
                double lastPrice = ticker.get("last").asDouble();
                double openPrice = ticker.get("open24h").asDouble();

                // Вычисление процента изменения цены
                double priceChangePercent = ((lastPrice - openPrice) / openPrice) * 100;

                crypto.setLastPrice(lastPrice);
                crypto.setPriceChangePercent(priceChangePercent);
                crypto.setVolume(ticker.get("vol24h").asDouble());
                crypto.setHighPrice(ticker.get("high24h").asDouble());
                crypto.setLowPrice(ticker.get("low24h").asDouble());

                parsedData.add(crypto);  // Добавляем объект в список
            }
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
