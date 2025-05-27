package com.sleepkqq.deepsearch.tools.logic.yandex;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sleepkqq.deepsearch.tools.config.api.YandexConfig;
import com.sleepkqq.deepsearch.tools.model.MediaFile;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import one.util.streamex.StreamEx;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class YandexDiskService {

  private final RestTemplate restTemplate;
  private final ObjectMapper objectMapper;
  private final YandexConfig yandexConfig;

  public List<MediaFile> fetchAllMediaFiles() {
    return fetchAllMediaFiles(Optional.empty());
  }

  public List<MediaFile> fetchMediaFilesByMediaType(String mediaType) {
    return fetchAllMediaFiles(Optional.of(mediaType));
  }

  private List<MediaFile> fetchAllMediaFiles(Optional<String> mediaType) {
    var response = getRequest(
        "https://cloud-api.yandex.net/v1/disk/resources/files"
            + mediaType.map(m -> "?media_type=" + m)
            .orElse("")
    );
    var jsonNode = toJsonNode(response.getBody());
    return StreamEx.of(jsonNode.path("items").elements())
        .map(e -> new MediaFile(
            e.path("text").asText(),
            e.path("file").asText(),
            e.path("media_type").asText(),
            MimeTypeUtils.parseMimeType(e.path("mime_type").asText())
        ))
        .toList();
  }

  private JsonNode toJsonNode(String string) {
    try {
      return objectMapper.readValue(string, JsonNode.class);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private ResponseEntity<String> getRequest(String url) {
    var headers = new HttpHeaders();
    headers.set("Authorization", "OAuth " + yandexConfig.getToken());

    var entity = new HttpEntity<>(headers);

    return restTemplate.exchange(
        url,
        HttpMethod.GET,
        entity,
        String.class
    );
  }
}
