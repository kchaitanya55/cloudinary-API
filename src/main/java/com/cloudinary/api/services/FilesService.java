package com.cloudinary.api.services;

import com.cloudinary.api.exception.util.CustomException;
import com.cloudinary.api.model.FileDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface FilesService {
    ResponseEntity uploadFileToCloudinary(MultipartFile file) throws IOException, CustomException;
}
