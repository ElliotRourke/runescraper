package com.runescaper.shell.configuration;

import com.runescaper.shell.controller.InputReader;
import org.jline.reader.LineReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class ShellConfiguration {
    @Bean
    public InputReader inputRead(@Lazy LineReader lineReader) {
        return new InputReader(lineReader);
    }
}
