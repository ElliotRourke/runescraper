package com.runescaper.shell.priceengine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ItemDAO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RunescapePriceResource implements PriceResource {

    private final WebClient runescapeWebClient;
    private ObjectMapper objectMapper;

    public RunescapePriceResource(WebClient runescapeWebClient) {
        this.runescapeWebClient = runescapeWebClient;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public ItemPrice getCurrentPrice(String itemName) {
        try {
            ItemDAO itemDAO = query(itemName);
        } catch (JsonProcessingException e) {
            return new ItemPrice();
        }
        return null;
    }

    //TODO: Add error handling, pojo mapping
    private ItemDAO query(String itemId) throws JsonProcessingException {
        String response = runescapeWebClient
                .get()
                .uri("/detail.json?item={itemId}", itemId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return objectMapper.readValue(response, ItemDAO.class);
    }
}
