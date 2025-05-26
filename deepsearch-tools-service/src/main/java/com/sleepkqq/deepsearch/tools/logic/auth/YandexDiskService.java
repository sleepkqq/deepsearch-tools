package com.sleepkqq.deepsearch.tools.logic.auth;

import static org.springframework.http.HttpMethod.GET;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sleepkqq.deepsearch.tools.config.api.YandexConfig;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class YandexDiskService {

  private final RestTemplate restTemplate;
  private final ObjectMapper objectMapper;
  private final YandexConfig yandexConfig;

  protected ResponseEntity<String> getRequest() {
    var headers = new HttpHeaders();
    headers.set("Authorization", "OAuth " + yandexConfig.getToken());

    var entity = new HttpEntity<>(headers);

    var apiUrl = "https://cloud-api.yandex.net/v1/disk/resources/files";
    return restTemplate.exchange(
        apiUrl,
        HttpMethod.GET,
        entity,
        String.class
    );
  }

  public void listFiles() {
    ResponseEntity<String> response = getRequest();
    if (response.getStatusCode() == HttpStatus.OK) {
      System.out.println("Ответ от Яндекс.Диска:");
      System.out.println(toJsonNode(response.getBody()).toPrettyString());
    } else {
      System.err.println("Ошибка при запросе к Яндекс.Диску: " + response.getStatusCode());
    }
  }

  public InputStream getInputStreamFromUrl(String fileUrl) {
    var headers = new HttpHeaders();
    headers.setBasicAuth("OAuth " + yandexConfig.getToken());

    return restTemplate.execute(
        fileUrl,
        GET,
        request -> request.getHeaders().addAll(headers),
        response -> {
          if (response.getStatusCode().is2xxSuccessful()) {
            var buffer = new ByteArrayOutputStream();
            try (InputStream inputStream = response.getBody()) {
              var data = new byte[1024];
              int bytesRead;
              while ((bytesRead = inputStream.read(data)) != -1) {
                buffer.write(data, 0, bytesRead);
              }
            }
            return new ByteArrayInputStream(buffer.toByteArray());
          } else {
            throw new RuntimeException("Failed to download file: HTTP " + response.getStatusCode());
          }
        }
    );
  }

  public JsonNode toJsonNode(String string) {
    try {
      return objectMapper.readValue(string, JsonNode.class);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
