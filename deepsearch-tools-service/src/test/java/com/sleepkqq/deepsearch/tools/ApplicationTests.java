package com.sleepkqq.deepsearch.tools;

import com.sleepkqq.deepsearch.tools.logic.ai.MediaAgent;
import com.sleepkqq.deepsearch.tools.logic.auth.YandexDiskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

  @Autowired
  YandexDiskService yandexDiskService;

  @Autowired
  MediaAgent mediaAgent;

  @Test
  void contextLoads() {
    yandexDiskService.listFiles();
  }

  @Test
  void mediaFile() {
    var inputStream = yandexDiskService.getInputStreamFromUrl("https://s51vla.storage.yandex.net/rdisk/040c631bcbb85efe7ae1e9357e382d0d61070ab9588319f34792a8297922ec6c/68262cb3/8Ng7aqpQFqID9pDAoIfkzJNiNSME7Q0O0DrXr5-IhMhsc2sfDe4WYN63DQe_2uMcRJgJ9rEomrz3OKR_LVAEqg==?uid=1478116516&filename=%D0%9C%D0%BE%D1%81%D0%BA%D0%B2%D0%B0.jpg&disposition=inline&hash=&limit=0&content_type=image%2Fjpeg&owner_uid=1478116516&fsize=1454228&hid=427d5900ad807ada37f94079f0fe7806&media_type=image&tknv=v3&etag=d27d72a3059ad5ebed7a5470459d2670&ts=635307f8e52c0&s=394cf003976031fa1203b7cdfa70fd2c0f372bf1617b684023a2bcf403ffcfa4&pb=U2FsdGVkX194XOHyGPMhGMT68xZatgCxNkoR4QoEBLjB3Hrh4fzYIBB7EZmL4A1VBQCakRlFfP5aB-zlWqYhEujwRQM-3F1jMbpvnNdNGG4");
    var result = mediaAgent.call(inputStream, "image/jpeg");
    System.out.println(result);
  }
}
