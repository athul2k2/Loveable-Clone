package com.codingshuttle.projects.loveable_clone.dto.project;

import java.time.Instant;

public record FileNode (
        String path,
        Instant modifiedAt,
        Long size,
        String type
){
}
