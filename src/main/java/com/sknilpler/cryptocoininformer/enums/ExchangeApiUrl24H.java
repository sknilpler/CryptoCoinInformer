package com.sknilpler.cryptocoininformer.enums;

/**
 * Enum, содержащий URL API для 24-часовой статистики каждой криптовалютной биржи.
 */
public enum ExchangeApiUrl24H {
    BINANCE_24H("https://api.binance.com/api/v3/ticker/24hr"),
    BITMART_24H("https://api-cloud.bitmart.com/spot/v1/ticker"),
    GATEIO_24H("https://api.gateio.ws/api/v4/spot/tickers"),
    HUOBI_24H("https://api.huobi.pro/market/tickers"),
    KUCOIN_24H("https://api.kucoin.com/api/v1/market/allTickers"),
    MEXC_24H("https://www.mexc.com/open/api/v2/market/ticker"),
    OKX_24H("https://www.okx.com/api/v5/market/tickers?instType=SPOT"),
    XT_24H("https://sapi.xt.com/v4/public/ticker/24h"),
    COINEX_24H("https://api.coinex.com/v1/market/ticker/all");

    private final String url;

    ExchangeApiUrl24H(String url) {
        this.url = url;
    }

    /**
     * Возвращает URL для 24-часовой статистики биржи.
     *
     * @return URL в виде строки.
     */
    public String getUrl24H() {
        return url;
    }
}
