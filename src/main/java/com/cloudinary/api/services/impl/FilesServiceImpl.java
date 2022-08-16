package com.cloudinary.api.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.exception.util.CustomException;
import com.cloudinary.api.model.Response;
import com.cloudinary.api.model.FileDetails;
import com.cloudinary.api.repositories.FilesRepository;
import com.cloudinary.api.services.FilesService;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
import java.util.Map;

@Service
public class FilesServiceImpl implements FilesService {
    @Autowired
    private FilesRepository filesRepository;

    @Value("${cloud.name}")
    private String cloudName;

    @Value("${cloud.api.key}")
    private String apiKey;

    @Value("${cloud.api.secret}")
    private String apiSecret;

    @Override
    public ResponseEntity uploadFileToCloudinary(MultipartFile file) throws IOException, CustomException {
        String extension=StringUtils.getFilenameExtension(file.getOriginalFilename());
        if (file.isEmpty() || !Arrays.asList("png","jpeg","jpg","gif").contains(extension.toLowerCase())) {
            throw new CustomException("Please upload Valid and Only Image Files");
        }
        Map options = ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret);
        Cloudinary cloudinary = new Cloudinary(options);
        Map cresponse = cloudinary.uploader().upload(file.getBytes(), options);
        FileDetails fileDetails = new FileDetails();
        fileDetails.setName(file.getName());
        fileDetails.setCreatedAt(new Date(System.currentTimeMillis()));
        fileDetails.setUrl((String) cresponse.get("secure_url"));
        filesRepository.save(fileDetails);
        Response response = new Response();
        response.setMessage("File uploaded successfully to Cloudinary");
        response.setStatus(HttpStatus.OK.value());
        response.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
