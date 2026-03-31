package com.codingshuttle.projects.loveable_clone.dto.project;

import java.util.List;

public  record FileTreeResponse(
        List<FileNode> fileNodeList
) {
}
