package com.runescaper.shell.controller;

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
    public String query(String itemId) {
        ItemDAO itemPrice = runescapePriceResource.query(itemId);
        return "" + itemPrice.current.price;
    }
}
