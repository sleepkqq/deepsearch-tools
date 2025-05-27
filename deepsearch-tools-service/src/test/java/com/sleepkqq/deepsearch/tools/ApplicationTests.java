package com.sleepkqq.deepsearch.tools;

import com.sleepkqq.deepsearch.tools.logic.yandex.YandexDiskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

  @Autowired
  YandexDiskService yandexDiskService;

  @Test
  void contextLoads() {
    yandexDiskService.fetchMediaFilesByMediaType("image")
        .forEach(System.out::println);
  }
}
