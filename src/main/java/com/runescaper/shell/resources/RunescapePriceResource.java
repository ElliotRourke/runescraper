package com.runescaper.shell.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.runescaper.shell.dao.ItemDAO;
import com.runescaper.shell.model.ItemPrice;
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
        ItemPrice itemPrice = new ItemPrice();
        try {
            ItemDAO dao = query(itemName);
            itemPrice.price = Integer.parseInt(dao.item.current.price);
            itemPrice.itemName = dao.item.name;
            itemPrice.itemId = dao.item.id;
        } catch (JsonProcessingException e) {
            return new ItemPrice();
        }
        return itemPrice;
    }

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
