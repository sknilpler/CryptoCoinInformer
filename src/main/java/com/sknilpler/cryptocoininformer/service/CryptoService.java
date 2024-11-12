package com.sknilpler.cryptocoininformer.service;

import com.sknilpler.cryptocoininformer.model.ExchangeData;
import com.sknilpler.cryptocoininformer.model.Ticker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

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


    public List<ExchangeData> getAllExchangesData() {
        ExecutorService executor = Executors.newFixedThreadPool(10); // Пул потоков для параллельного выполнения
        try {
            System.out.println("Получение данных с API бирж...");
            long startTime = System.currentTimeMillis();

            List<CompletableFuture<ExchangeData>> futures = List.of(
                    CompletableFuture.supplyAsync(() -> new ExchangeData("Binance", binanceClient.getParsing24HoursData()), executor),
                    CompletableFuture.supplyAsync(() -> new ExchangeData("BitMart", bitMartClient.getParsing24HoursData()), executor),
                    CompletableFuture.supplyAsync(() -> new ExchangeData("CoinEx", coinExClient.getParsing24HoursData()), executor),
                    CompletableFuture.supplyAsync(() -> new ExchangeData("GateIo", gateIoClient.getParsing24HoursData()), executor),
                    CompletableFuture.supplyAsync(() -> new ExchangeData("Huobi", huobiClient.getParsing24HoursData()), executor),
                    CompletableFuture.supplyAsync(() -> new ExchangeData("KuCoin", kuCoinClient.getParsing24HoursData()), executor),
                    CompletableFuture.supplyAsync(() -> new ExchangeData("Mexc", mexcClient.getParsing24HoursData()), executor),
                    CompletableFuture.supplyAsync(() -> new ExchangeData("OKX", okxClient.getParsing24HoursData()), executor),
                    CompletableFuture.supplyAsync(() -> new ExchangeData("XtCom", xtComClient.getParsing24HoursData()), executor)
            );

            // Ожидание завершения всех задач и сбор результатов
            List<ExchangeData> allData = futures.stream()
                    .map(CompletableFuture::join)
                    .collect(Collectors.toList());

            long endTime = System.currentTimeMillis();
            System.out.println("Данные с бирж получены!\nВремени затрачено: " + (endTime - startTime) + " мс");
            System.out.println("Кол-во полученных записей пар:");
            allData.forEach(data -> System.out.println("\t" + data.getExchangeName() + ": " + data.getTickers().size()));

            return allData;
        } catch (Exception e) {
            System.out.println("Не удалось получить данные с бирж!");
            throw new RuntimeException(e);
        } finally {
            executor.shutdown(); // Завершение пула потоков
        }
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

}
