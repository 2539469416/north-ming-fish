package com.xfa.controller;

import com.xfa.service.FileUploadService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author liwen
 */
@RestController
public class FileUploadController {
    @Resource
    private FileUploadService fileUploadService;
    @PostMapping("/csv/upload")
    public ResponseEntity csvUpload(MultipartFile[] files) {
        fileUploadService.getFilesList(files);
        return ResponseEntity.ok(0);
    }
}
