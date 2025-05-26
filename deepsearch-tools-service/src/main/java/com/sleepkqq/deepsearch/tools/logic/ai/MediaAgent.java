package com.sleepkqq.deepsearch.tools.logic.ai;

import static com.sleepkqq.deepsearch.tools.logic.ai.Prompts.MEDIA_AGENT_PROMPT;

import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
@RequiredArgsConstructor
public class MediaAgent {

  private final ChatClient chatClient;

  public String call(InputStream imageInputStream, String contentType) {
    return chatClient.prompt()
        .system(MEDIA_AGENT_PROMPT)
        .user(userMessage -> userMessage
            .media(MimeTypeUtils.parseMimeType(contentType), new InputStreamResource(imageInputStream))
        )
        .call()
        .content();
  }
}
