package com.sleepkqq.deepsearch.tools.config.mcp;

import com.sleepkqq.deepsearch.tools.logic.mcp.DeepsearchMcpTools;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpConfig {

  @Bean
  ToolCallbackProvider deepsearchTools(DeepsearchMcpTools deepsearchMcpTools) {
    return MethodToolCallbackProvider.builder()
        .toolObjects(deepsearchMcpTools)
        .build();
  }
}
