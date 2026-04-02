package com.codingshuttle.projects.loveable_clone.entity;

import com.codingshuttle.projects.loveable_clone.enums.MessageRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMessage {

    Long id;
    ChatSession chatSession;
    String content;
    String toolCalls;  // JSON Array of tools Called

    MessageRole role;

    Integer tokenUsed;

    Instant createdAt;
}
