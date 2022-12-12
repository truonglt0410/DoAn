package com.edu.fpt.hoursemanager.management.file.service.impl;

import com.edu.fpt.hoursemanager.common.entity.AccountLoginCommon;
import com.edu.fpt.hoursemanager.common.utils.Const;
import com.edu.fpt.hoursemanager.management.file.entity.File;
import com.edu.fpt.hoursemanager.management.file.repository.FileRepository;
import com.edu.fpt.hoursemanager.management.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

@Service
public class FileServiceImpl implements FileService {
    private final  Path root = Paths.get(Const.UPLOAD_URL);

    @Autowired
    private FileRepository fileRepository;

    //************************************
    // create Directory to save file
    //************************************
    @Override
    public void init() {
        try {
            if(!Files.exists(root)) {
                Files.createDirectory(root);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }
    //************************************
    // Save file
    //************************************
    @Override
    public void save(MultipartFile file) {
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            File file1 = new File();
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            file1.setModified_by(accountLoginCommon.getUserName());
            file1.setCreated_by(accountLoginCommon.getUserName());
            file1.setName(fileName);
            file1.setType(file.getContentType());
            file1.setUrl(root.toUri().getPath());
            fileRepository.save(file1);
            Files.copy(file.getInputStream(), this.root.resolve(fileName));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }
    //************************************
    // Load file
    //************************************
    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
    //************************************
    // Get uri of file
    //************************************
    @Override
    public String loadUri(String filename) {
        try {
            Path file = root.resolve(filename);
            return file.toUri().getPath();
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
    //************************************
    // Delete all file of Directory
    //************************************
    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }
    //************************************
    // Save image of Directory
    //************************************
    @Override
    public File saveImage(MultipartFile file) {
        File file1 = new File();
        try {
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy_hh-mm-ss");
            String dateTimeAfterFormat = dateTime.format(dateTimeFormat);
            String fileName = "file_" + dateTimeAfterFormat + "_" + StringUtils.cleanPath(file.getOriginalFilename());
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            file1.setModified_by(accountLoginCommon.getUserName());
            file1.setCreated_by(accountLoginCommon.getUserName());
            file1.setName(fileName);
            file1.setType(file.getContentType());
            file1.setUrl(root.toUri().getPath());
            file1 = fileRepository.save(file1);
            Files.copy(file.getInputStream(), this.root.resolve(fileName));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
        return file1;
    }
    //************************************
    // Delete file by fileId
    //************************************
    @Override
    public void deleteFile(String filename) {
        try {
            fileRepository.deleteByName(filename);
            Path file = root.resolve(filename);
            FileSystemUtils.deleteRecursively(file);
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
