package com.sknilpler.cryptocoininformer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
public class ExchangeData {
    private String exchangeName;
    private List<Ticker> tickers;

    public ExchangeData(String exchangeName, List<Ticker> tickers) {
        this.exchangeName = exchangeName;
        this.tickers = tickers;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public List<Ticker> getTickers() {
        return tickers;
    }

    public void setTickers(List<Ticker> tickers) {
        this.tickers = tickers;
    }

    @Override
    public String toString() {
        return "ExchangeData{" +
                "exchangeName='" + exchangeName + '\'' +
                ", tickers=" + tickers +
                '}';
    }
}
