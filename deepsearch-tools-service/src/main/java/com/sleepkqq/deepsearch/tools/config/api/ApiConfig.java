package com.sleepkqq.deepsearch.tools.config.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApiConfig {

  RestTemplate restTemplate() {
    return new RestTemplate();
  }

  ObjectMapper objectMapper() {
    return new ObjectMapper();
  }
}
