package com.runescaper.shell.priceengine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ItemDAO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RunescapePriceResource {

    private final WebClient runescapeWebClient;

    public RunescapePriceResource(WebClient runescapeWebClient) {
        this.runescapeWebClient = runescapeWebClient;
    }

    //TODO: Add error handling, pojo mapping
    public ItemDAO query(String itemId) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String response = runescapeWebClient
                .get()
                .uri("/detail.json?item={itemId}",itemId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return objectMapper.readValue(response, ItemDAO.class);
    }

}
