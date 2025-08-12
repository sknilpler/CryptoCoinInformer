package com.sknilpler.cryptocoininformer.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FormattingSymbol {
    // Список известных базовых валют
    private static final Set<String> baseCurrencies = new HashSet<>(Arrays.asList(
            "BTC", "ETH", "USDT", "BNB", "LTC", "XRP", "DOGE", "ADA", "DOT", "SOL", "SHIB", "TRX", "MATIC",
            "BCH", "XLM", "LINK", "ATOM", "UNI", "FIL", "ALGO", "VET", "FTM", "AVAX", "EGLD", "XTZ", "FDUSD",
            "NEAR", "AAVE", "GRT", "SAND", "MANA", "THETA", "AXS", "KSM", "ZEC", "DASH", "COMP", "CRV",
            "SNX", "RUNE", "ENJ", "KAVA", "MKR", "YFI", "1INCH", "BAT", "CHZ", "HNT", "ICX", "ONT",
            "ZIL", "IOST", "WAVES", "QTUM", "ZRX", "LUNA", "CAKE", "XEC", "GALA", "CELO", "KLAY", "XAI",
            "XNO", "LRC", "BTT", "STMX", "ANKR", "ROSE", "JASMY", "RSR", "KNC", "GNO", "ENS", "OMG", "WIF", "DYM",
            "FLOW", "INJ", "CRO", "RVN", "CSPR", "SUSHI", "XVG", "TWT", "CELR", "QNT", "DYDX", "NKN", "BUSD",
            "DGB", "LSK", "REP", "POLY", "STORJ", "RAY", "MINA", "GTC", "PYR", "SPELL", "LPT", "KEEP", "ERD",
            "SRM", "OCEAN", "UMA", "BNT", "BAL", "ORN", "MTL", "KSM", "SKL", "REEF", "CTSI", "DENT", "GBP", "PHB"
    ));
    // Метод для приведения символа в формат "BASE-QUOTE"
    public static String formatSymbol(String symbol) {
        // Если символ содержит разделители (_ / -), используем стандартный метод разделения
        if (symbol.contains("_") || symbol.contains("-") || symbol.contains("/")) {
            String[] parts = symbol.split("[_/:-]");
            if (parts.length == 2) {
                return parts[0].toUpperCase() + "-" + parts[1].toUpperCase();
            }
        }

        // Если символ слитно (например, "BTCUSDT"), разделяем по известным базовым валютам
        for (String base : baseCurrencies) {
            // Если базовая валюта находится в начале
            if (symbol.startsWith(base.toUpperCase())) {
                String quote = symbol.substring(base.length());
                return base.toUpperCase() + "-" + quote.toUpperCase();
            }
            // Если базовая валюта находится в конце
            if (symbol.endsWith(base.toUpperCase())) {
                String quote = symbol.substring(0, symbol.length() - base.length());
                return quote.toUpperCase() + "-" + base.toUpperCase();
            }
        }

        // Если не удалось распознать символ, возвращаем его как есть
        return symbol;
    }

}
