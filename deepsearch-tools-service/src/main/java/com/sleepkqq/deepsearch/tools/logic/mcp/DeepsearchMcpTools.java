package com.sleepkqq.deepsearch.tools.logic.mcp;

import com.sleepkqq.deepsearch.tools.logic.yandex.YandexDiskService;
import com.sleepkqq.deepsearch.tools.model.File;
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

  @Tool(description = "Get all files")
  public List<File> getAllFiles() {
    return yandexDiskService.fetchAllFiles();
  }

  @Tool(description = "Get all images")
  public List<File> getAllImages() {
    return yandexDiskService.fetchFilesByMediaType("image");
  }

  @Tool(description = "Get all data files")
  public List<File> getAllDataFiles() {
    return yandexDiskService.fetchFilesByMediaType("data");
  }
}
