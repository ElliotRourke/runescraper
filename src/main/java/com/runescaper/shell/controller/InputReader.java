package com.runescaper.shell.controller;

import org.jline.reader.LineReader;
import org.springframework.util.StringUtils;

import java.util.List;


public class InputReader {
    public static final Character DEFAULT_MASK = '*';

    private final Character mask;
    private final LineReader lineReader;

    public InputReader(LineReader lineReader) {
        this(lineReader, null);
    }

    public InputReader(LineReader lineReader, Character mask) {
        this.lineReader = lineReader;
        this.mask = mask != null ? mask : DEFAULT_MASK;
    }

    public String prompt(String prompt) {
        return prompt(prompt, null, true);
    }

    public String prompt(String prompt, String defaultValue) {
        return prompt(prompt, defaultValue, true);
    }

    public int promptListOptions(String prompt, List<String> options) throws InvalidPromptResponseException {

        StringBuilder promptBuilder = new StringBuilder(prompt);
        for (int i = 0; i < options.size(); i++) {
            promptBuilder.append("\n")
                    .append(i + 1)
                    .append(") ")
                    .append(options.get(i));
        }

        promptBuilder.append("\n");
        promptBuilder.append("\n:");
        prompt = promptBuilder.toString();
        int s = promptInt(prompt);

        if (s < 1 || s > options.size()) {
            throw new InvalidPromptResponseException("Invalid response. Expected integer of range 1 - " + options.size() + " instead got integer of size " + s);
        }

        return s - 1;
    }

    public int promptInt(String prompt) throws InvalidPromptResponseException {
        try {
            String response = prompt(prompt);
            return Integer.parseInt(response);
        } catch (NumberFormatException e) {
            throw new InvalidPromptResponseException("Expected type=Integer, instead got type=String.");
        }
    }

    public String prompt(String prompt, String defaultValue, boolean echo) {
        String answer;
        if (echo) {
            answer = lineReader.readLine(prompt + ": ");
        } else {
            answer = lineReader.readLine(prompt + ": ", mask);
        }
        if (StringUtils.isEmpty(answer)) {
            return defaultValue;
        }
        return answer;
    }
}