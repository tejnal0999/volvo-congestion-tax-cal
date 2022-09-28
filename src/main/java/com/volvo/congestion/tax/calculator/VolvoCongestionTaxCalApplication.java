package com.volvo.congestion.tax.calculator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.zalando.problem.jackson.ProblemModule;

@SpringBootApplication
public class VolvoCongestionTaxCalApplication {

  public static void main(String[] args) {
    SpringApplication.run(VolvoCongestionTaxCalApplication.class, args);
  }

  @Bean
  public ObjectMapper objectMapper() {

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    objectMapper.registerModule(new JavaTimeModule())
            .registerModule(new ProblemModule());
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    return objectMapper;
  }
}
