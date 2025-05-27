package com.sleepkqq.deepsearch.tools.logic.mcp;

import com.sleepkqq.deepsearch.tools.logic.yandex.YandexDiskService;
import com.sleepkqq.deepsearch.tools.model.MediaFile;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeepsearchMcpTools {

  private final YandexDiskService yandexDiskService;

  @Tool(description = "Get all media files")
  public List<MediaFile> getAllMediaFiles() {
    return yandexDiskService.fetchAllMediaFiles();
  }

  @Tool(description = "Get all images")
  public List<MediaFile> getAllImages() {
    return yandexDiskService.fetchMediaFilesByMediaType("image");
  }

  @Tool(description = "Get all data files")
  public List<MediaFile> getAllDataFiles() {
    return yandexDiskService.fetchMediaFilesByMediaType("data");
  }
}
