package com.sknilpler.cryptocoininformer.controller;

import com.sknilpler.cryptocoininformer.model.ExchangeData;
import com.sknilpler.cryptocoininformer.model.Ticker;
import com.sknilpler.cryptocoininformer.service.CryptoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/crypto")
public class CryptoController {
    @Autowired
    private CryptoService cryptoService;

    @Operation(summary = "Получить данные всех бирж", description = "Возвращает список криптовалютных данных за 24 часа со всех поддерживаемых бирж в формате JSON.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение данных"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @GetMapping("/all")
    public ResponseEntity<List<ExchangeData>> getAllExchangesData() {
        List<ExchangeData> allData = cryptoService.getAllExchangesData();
        return ResponseEntity.ok(allData);
    }

    @Operation(summary = "Получить данные с Binance", description = "Возвращает список криптовалютных данных за 24 часа с Binance в формате JSON.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение данных"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @GetMapping("/binance")
    public ResponseEntity<List<Ticker>> getBinanceData() {
        List<Ticker> binanceData = cryptoService.getBinanceData();
        return ResponseEntity.ok(binanceData);
    }

    @Operation(summary = "Получить данные с BitMart", description = "Возвращает список криптовалютных данных за 24 часа с BitMart в формате JSON.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение данных"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @GetMapping("/bitmart")
    public ResponseEntity<List<Ticker>> getBitmartData() {
        return ResponseEntity.ok(cryptoService.getBitMartData());
    }

    @Operation(summary = "Получить данные с CoinEx", description = "Возвращает список криптовалютных данных за 24 часа с CoinEx в формате JSON.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение данных"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @GetMapping("/coinex")
    public ResponseEntity<List<Ticker>> getCoinExData() {
        return ResponseEntity.ok(cryptoService.getCoinExData());
    }

    @Operation(summary = "Получить данные с Gate.Io", description = "Возвращает список криптовалютных данных за 24 часа с Gate.Io в формате JSON.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение данных"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @GetMapping("/gateio")
    public ResponseEntity<List<Ticker>> getGateIoData() {
        return ResponseEntity.ok(cryptoService.getGateIoData());
    }

    @Operation(summary = "Получить данные с Huobi", description = "Возвращает список криптовалютных данных за 24 часа с Huobi в формате JSON.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение данных"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @GetMapping("/huobi")
    public ResponseEntity<List<Ticker>> getHuobiData() {
        return ResponseEntity.ok(cryptoService.getHuobiData());
    }

    @Operation(summary = "Получить данные с KuCoin", description = "Возвращает список криптовалютных данных за 24 часа с KuCoin в формате JSON.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение данных"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @GetMapping("/kucoin")
    public ResponseEntity<List<Ticker>> getKuCoinData() {
        return ResponseEntity.ok(cryptoService.getKuCoinExData());
    }

    @Operation(summary = "Получить данные с Mexc", description = "Возвращает список криптовалютных данных за 24 часа с Mexc в формате JSON.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение данных"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @GetMapping("/mexc")
    public ResponseEntity<List<Ticker>> getMexcData() {
        return ResponseEntity.ok(cryptoService.getMexcData());
    }

    @Operation(summary = "Получить данные с OKX", description = "Возвращает список криптовалютных данных за 24 часа с OKX в формате JSON.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение данных"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @GetMapping("/okx")
    public ResponseEntity<List<Ticker>> getOkxData() {
        return ResponseEntity.ok(cryptoService.getOkxData());
    }

    @Operation(summary = "Получить данные с XT.com", description = "Возвращает список криптовалютных данных за 24 часа с XT.com в формате JSON.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение данных"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    @GetMapping("/xtcom")
    public ResponseEntity<List<Ticker>> getXtComData() {
        return ResponseEntity.ok(cryptoService.getXtComData());
    }

}
