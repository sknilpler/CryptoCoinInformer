package com.sknilpler.cryptocoininformer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ExchangeData {
    private String exchangeName;
    private List<Ticker> tickers;

    public ExchangeData(String exchangeName, List<Ticker> tickers) {
        this.exchangeName = exchangeName;
        this.tickers = tickers;
    }
}
