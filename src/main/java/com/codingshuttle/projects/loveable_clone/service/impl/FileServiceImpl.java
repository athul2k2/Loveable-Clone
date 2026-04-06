package com.codingshuttle.projects.loveable_clone.service.impl;

import com.codingshuttle.projects.loveable_clone.dto.project.FileContentResponse;
import com.codingshuttle.projects.loveable_clone.dto.project.FileNode;
import com.codingshuttle.projects.loveable_clone.service.FileService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FileServiceImpl implements FileService {
    @Override
    public List<FileNode> getFileTree(Long projectId, Long userId) {
        return List.of();
    }

    @Override
    public FileContentResponse getFileContent(long projectId, String path, Long userId) {
        return null;
    }
}
