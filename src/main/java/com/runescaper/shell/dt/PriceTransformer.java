package com.runescaper.shell.dt;

import com.runescaper.shell.priceengine.ItemPrice;
import com.runescaper.shell.priceengine.PriceResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PriceTransformer implements Transformer {
    Map<String, PriceResource> priceResourceMap = new HashMap<>();

    @Autowired
    public PriceTransformer(PriceResource geTrackerResource, PriceResource osrsResource) {
        priceResourceMap.put("GE-Tracker", geTrackerResource);
        priceResourceMap.put("Runescape Official", osrsResource);
    }

    @Override
    public ItemPrice getCurrentPrice(String source, String item) {
        return priceResourceMap.get(source).getCurrentPrice(item);
    }

    @Override
    public List<String> getSources() {
        return Arrays.asList("GE-Tracker", "Runescape Official");
    }
}
