package com.runescaper.shell.resources;

import com.runescaper.shell.model.ItemPrice;

public interface PriceResource {
    ItemPrice getCurrentPrice(String itemName);
}
