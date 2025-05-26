package com.sleepkqq.deepsearch.tools.config.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "app.yandex.api")
public class YandexConfig {

  private String token;
}
