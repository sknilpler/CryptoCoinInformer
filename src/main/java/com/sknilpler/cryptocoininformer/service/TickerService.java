package com.sknilpler.cryptocoininformer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.sknilpler.cryptocoininformer.model.Ticker;

import java.util.List;

/**
 * Интерфейс для сервисов, получающих и парсящих 24-часовую статистику данных с различных криптовалютных бирж.
 */
public interface TickerService {

    /**
     * Получает данные за 24 часа от биржи в формате JsonNode.
     *
     * @return JsonNode данные за 24 часа в формате JSON.
     * @throws Exception если возникла ошибка при получении данных.
     */
    JsonNode get24HourData() throws Exception;

    /**
     * Парсит данные и возвращает список объектов Ticker.
     *
     * @param data данные для парсинга в формате JsonNode.
     * @return список объектов Ticker, содержащий распарсенные данные.
     */
    List<Ticker> parseData(JsonNode data);

    /**
     * Получает и парсит данные за 24 часа, возвращая их в виде списка объектов Ticker.
     *
     * @return список объектов Ticker с 24-часовыми данными.
     */
    List<Ticker> getParsing24HoursData();
}
