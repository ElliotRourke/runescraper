package com.runescaper.shell.dt;

import com.runescaper.shell.priceengine.ItemPrice;

import java.util.List;

public interface Transformer {

    ItemPrice getCurrentPrice(String source, String item);

    List<String> getSources();
}
