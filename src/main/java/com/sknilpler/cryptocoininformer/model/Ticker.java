package com.sknilpler.cryptocoininformer.model;

/**
 * Класс Ticker представляет данные о торговом тикере.
 */
public class Ticker {
    /**
     * Символ тикера, представляющий торговую пару (например, "BTC/USDT").
     */
    private String symbol;

    /**
     * Последняя цена тикера на момент запроса.
     */
    private double lastPrice;

    /**
     * Цена открытия за выбранный период.
     */
    private double openPrice;

    /**
     * Процент изменения цены за выбранный период.
     */
    private double priceChangePercent;

    /**
     * Максимальная цена за выбранный период.
     */
    private double highPrice;

    /**
     * Минимальная цена за выбранный период.
     */
    private double lowPrice;

    /**
     * Объем торгов за выбранный период.
     */
    private double volume;

    public Ticker() {
    }

    /**
     * Создает экземпляр класса Ticker, представляющего данные о торговом тикере.
     *
     * @param symbol символ тикера, представляющий торговую пару (например, "BTC/USDT").
     * @param lastPrice последняя цена тикера на момент запроса.
     * @param openPrice цена открытия за выбранный период.
     * @param priceChangePercent процент изменения цены за выбранный период.
     * @param highPrice максимальная цена за выбранный период.
     * @param lowPrice минимальная цена за выбранный период.
     * @param volume объем торгов за выбранный период.
     */
    public Ticker(String symbol, double lastPrice, double openPrice, double priceChangePercent, double highPrice, double lowPrice, double volume) {
        this.symbol = symbol;
        this.lastPrice = lastPrice;
        this.openPrice = openPrice;
        this.priceChangePercent = priceChangePercent;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.volume = volume;
    }

    /**
     * Возвращает символ тикера, представляющий торговую пару.
     *
     * @return символ тикера (например, "BTC/USDT").
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Устанавливает символ тикера, представляющий торговую пару.
     *
     * @param symbol символ тикера (например, "BTC/USDT").
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Возвращает последнюю цену тикера на момент запроса.
     *
     * @return последняя цена тикера.
     */
    public double getLastPrice() {
        return lastPrice;
    }

    /**
     * Устанавливает последнюю цену тикера.
     *
     * @param lastPrice последняя цена тикера.
     */
    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }

    /**
     * Возвращает цену открытия за выбранный период.
     *
     * @return цена открытия.
     */
    public double getOpenPrice() {
        return openPrice;
    }

    /**
     * Устанавливает цену открытия за выбранный период.
     *
     * @param openPrice цена открытия.
     */
    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    /**
     * Возвращает процент изменения цены за выбранный период.
     *
     * @return процент изменения цены.
     */
    public double getPriceChangePercent() {
        return priceChangePercent;
    }

    /**
     * Устанавливает процент изменения цены за выбранный период.
     *
     * @param priceChangePercent процент изменения цены.
     */
    public void setPriceChangePercent(double priceChangePercent) {
        this.priceChangePercent = priceChangePercent;
    }

    /**
     * Возвращает максимальную цену за выбранный период.
     *
     * @return максимальная цена.
     */
    public double getHighPrice() {
        return highPrice;
    }

    /**
     * Устанавливает максимальную цену за выбранный период.
     *
     * @param highPrice максимальная цена.
     */
    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    /**
     * Возвращает минимальную цену за выбранный период.
     *
     * @return минимальная цена.
     */
    public double getLowPrice() {
        return lowPrice;
    }

    /**
     * Устанавливает минимальную цену за выбранный период.
     *
     * @param lowPrice минимальная цена.
     */
    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }

    /**
     * Возвращает объем торгов за выбранный период.
     *
     * @return объем торгов.
     */
    public double getVolume() {
        return volume;
    }

    /**
     * Устанавливает объем торгов за выбранный период.
     *
     * @param volume объем торгов.
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Ticker{" +
                "symbol='" + symbol + '\'' +
                ", lastPrice=" + lastPrice +
                ", openPrice=" + openPrice +
                ", priceChangePercent=" + priceChangePercent +
                ", highPrice=" + highPrice +
                ", lowPrice=" + lowPrice +
                ", volume=" + volume +
                '}';
    }
}

