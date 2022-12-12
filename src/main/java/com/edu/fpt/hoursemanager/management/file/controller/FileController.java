package com.edu.fpt.hoursemanager.management.file.controller;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.file.entity.File;
import com.edu.fpt.hoursemanager.management.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "/files")
public class FileController {
    @Autowired
    private FileService fileService;
    //************************************
    // Upload file
    //************************************
    @PostMapping("/upload")
    public ResponseEntity<ResponseModels> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            fileService.save(file);
            return ResponseModels.created("Uploaded the file successfully: " + file.getOriginalFilename());
        } catch (Exception e) {
            return ResponseModels.error("Could not upload the file: " + file.getOriginalFilename()+ "-" + e.getMessage() + "!");
        }
    }
    //************************************
    // Upload file image
    //************************************
    @PostMapping("/image")
    public ResponseEntity<ResponseModels> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            File fileAfterSave = fileService.saveImage(file);
            return ResponseModels.created(fileAfterSave,"Uploaded the file successfully: " + file.getOriginalFilename());
        } catch (Exception e) {
            return ResponseModels.error("Could not upload the file: " + file.getOriginalFilename() + "-" + e.getMessage()  + "!");
        }
    }
    //************************************
    // Read excel
    //************************************

    @PostMapping("/readexcel")
    public ResponseEntity<ResponseModels> readExcel(@RequestParam("file") MultipartFile file) {
        try {
            fileService.save(file);
            return ResponseModels.created("Uploaded the file successfully: " + file.getOriginalFilename());
        } catch (Exception e) {
            return ResponseModels.error("Could not upload the file: " + file.getOriginalFilename() + "-" + e.getMessage()  + "!");
        }
    }

    //************************************
    // Get file by name
    //************************************
    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws IOException {
        try {
            Resource file = fileService.load(filename);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        }catch (Exception exception){
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "null" + "\"").body(null);
        }
    }
    //************************************
    // Delete file by name
    //************************************
    @DeleteMapping("/{filename:.+}")
    public ResponseEntity<ResponseModels> deleteFile(@PathVariable String filename){
        try {
            fileService.deleteFile(filename);
            return ResponseModels.success("Delete the file successfully");
        } catch (Exception e) {
            return ResponseModels.error("Could not delete the file!"+ "-" + e.getMessage() );

        }
    }


}
