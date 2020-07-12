package com.runescaper.shell.controller;

import com.runescaper.shell.dt.Transformer;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class PricingController {
    private final InputReader inputReader;
    private final Transformer priceTransformer;
    private String activeSource = null;

    public PricingController(InputReader inputReader, Transformer priceTransformer) {
        this.inputReader = inputReader;
        this.priceTransformer = priceTransformer;
    }

    @ShellMethod(value = "Set the client source", group = "Price Acquisition Configuration")
    public String setSource() throws InvalidPromptResponseException {
        List<String> sources = priceTransformer.getSources();

        int prompt = inputReader.promptListOptions("Please seleect 1 of the following options", sources);
        this.activeSource = sources.get(prompt);
        return "Active source set to " + this.activeSource + ".";
    }

    @ShellMethod(value = "Request the current price", group = "Price Acquisition Configuration")
    public int getPrice(String item) throws SourceNotFoundException {
        if(activeSource == null) {
            throw new SourceNotFoundException("Source not set. You can set the current source using set-source");
        }
        return priceTransformer.getCurrentPrice(activeSource, item).getPrice();
    }
}
