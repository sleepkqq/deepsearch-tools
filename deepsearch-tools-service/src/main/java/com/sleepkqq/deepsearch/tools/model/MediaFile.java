package com.sleepkqq.deepsearch.tools.model;

import org.springframework.util.MimeType;

public record MediaFile(String name, String link, String mediaType, MimeType mimeType) {

}
