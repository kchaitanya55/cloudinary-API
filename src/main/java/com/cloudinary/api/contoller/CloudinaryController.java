package com.cloudinary.api.contoller;

import com.cloudinary.api.exception.util.CustomException;
import com.cloudinary.api.model.FileDetails;
import com.cloudinary.api.repositories.FilesRepository;
import com.cloudinary.api.services.FilesService;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cloudinary.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class CloudinaryController {
    @Autowired
    private FilesService filesService;

    @Autowired
    private FilesRepository filesRepository;


    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) throws IOException, CustomException {
        return filesService.uploadFileToCloudinary(file);
    }

    @GetMapping("/images")
    public List<FileDetails> getAllFiles() throws IOException {
        return filesRepository.findAll();
    }

}
