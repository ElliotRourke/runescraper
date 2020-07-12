package com.runescaper.shell.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.runescaper.shell.priceengine.RunescapePriceResource;
import dao.ItemDAO;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class PricingController {

    private RunescapePriceResource runescapePriceResource;

    public PricingController(RunescapePriceResource runescapePriceResource) {
        this.runescapePriceResource = runescapePriceResource;
    }

    //TODO: Remove
    @ShellMethod("Cool test")
    public String query(String itemId) throws JsonProcessingException {
        ItemDAO itemPrice = runescapePriceResource.query(itemId);
        return "" + itemPrice.item.current.price;
    }
}
