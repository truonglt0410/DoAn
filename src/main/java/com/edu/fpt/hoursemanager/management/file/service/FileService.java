package com.edu.fpt.hoursemanager.management.file.service;

import com.edu.fpt.hoursemanager.management.file.entity.File;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileService {
    void init();

    void save(MultipartFile file);

    Resource load(String filename);

    String loadUri(String filename);

    void deleteAll();

    Stream<Path> loadAll();

    File saveImage(MultipartFile file);

    void deleteFile(String fileId);
}
