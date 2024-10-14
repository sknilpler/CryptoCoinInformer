package com.sknilpler.cryptocoininformer.service;

import com.sknilpler.cryptocoininformer.model.ExchangeData;
import com.sknilpler.cryptocoininformer.model.Ticker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CryptoService {
    @Autowired
    private BinanceApiClient binanceClient;
    @Autowired
    private BitMartApiClient bitMartClient;
    @Autowired
    private CoinExApiClient coinExClient;
    @Autowired
    private GateIoApiClient gateIoClient;
    @Autowired
    private HuobiApiClient huobiClient;
    @Autowired
    private KuCoinApiClient kuCoinClient;
    @Autowired
    private MexcApiClient mexcClient;
    @Autowired
    private OkxApiClient okxClient;
    @Autowired
    private XtComApiClient xtComClient;
    // Другие API клиенты

    public List<ExchangeData> getAllExchangesData() {
        List<ExchangeData> allData = new ArrayList<>();
        try {
            System.out.println("Получение данных с API бирж...");
            long startTime = System.currentTimeMillis();
            allData.add(new ExchangeData("Binance", binanceClient.getParsing24HoursData()));
            allData.add(new ExchangeData("BitMart", bitMartClient.getParsing24HoursData()));
            allData.add(new ExchangeData("CoinEx", coinExClient.getParsing24HoursData()));
            allData.add(new ExchangeData("GateIo", gateIoClient.getParsing24HoursData()));
            allData.add(new ExchangeData("Huobi", huobiClient.getParsing24HoursData()));
            allData.add(new ExchangeData("KuCoin", kuCoinClient.getParsing24HoursData()));
            allData.add(new ExchangeData("Mexc", mexcClient.getParsing24HoursData()));
            allData.add(new ExchangeData("OKX", okxClient.getParsing24HoursData()));
            allData.add(new ExchangeData("XtCom", xtComClient.getParsing24HoursData()));
            // Другие биржи
            long endTime = System.currentTimeMillis();

            long timeElapsed = endTime - startTime;
            System.out.println("Данные с бирж получены!\nВремени затрачено: "+timeElapsed+" мс");
        } catch (Exception e) {
            System.out.println("Не удалось получить данные с бирж!");
            throw new RuntimeException(e);
        }

        return allData;
    }

    public List<Ticker> getBinanceData() {
        return binanceClient.getParsing24HoursData();
    }

    public List<Ticker> getBitMartData() {
        return bitMartClient.getParsing24HoursData();
    }

    public List<Ticker> getCoinExData() {
        return coinExClient.getParsing24HoursData();
    }

    public List<Ticker> getGateIoData() {
        return gateIoClient.getParsing24HoursData();
    }

    public List<Ticker> getHuobiData() {
        return huobiClient.getParsing24HoursData();
    }

    public List<Ticker> getKuCoinExData() {
        return kuCoinClient.getParsing24HoursData();
    }

    public List<Ticker> getMexcData() {
        return mexcClient.getParsing24HoursData();
    }

    public List<Ticker> getOkxData() {
        return okxClient.getParsing24HoursData();
    }
    public List<Ticker> getXtComData() {
        return xtComClient.getParsing24HoursData();
    }

    // Аналогичные методы для других бирж
}
