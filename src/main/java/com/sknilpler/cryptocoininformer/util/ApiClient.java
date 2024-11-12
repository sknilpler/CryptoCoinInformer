package com.sknilpler.cryptocoininformer.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

/**
 * Вспомогательный класс для выполнения HTTP-запросов к API.
 */
@Component
public class ApiClient {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Выполняет GET-запрос по заданному URL и возвращает ответ в виде JsonNode.
     *
     * @param url URL API, к которому выполняется запрос.
     * @return JsonNode, представляющий тело ответа.
     * @throws Exception при ошибке выполнения запроса.
     */
    public JsonNode getData(String url) throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = client.execute(request)) {
                String responseBody = EntityUtils.toString(response.getEntity());
                return MAPPER.readTree(responseBody);
            }
        }
    }
}
