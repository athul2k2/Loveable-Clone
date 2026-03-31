package com.codingshuttle.projects.loveable_clone.entity;

import com.codingshuttle.projects.loveable_clone.enums.PreviewStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Preview {

    Long id;

    Project project;

    String namespace;
    String podName;
    String preivewUrl;

    PreviewStatus status;

    Instant startedAt;
    Instant teminatedAt;

    Instant createdAt;

}
