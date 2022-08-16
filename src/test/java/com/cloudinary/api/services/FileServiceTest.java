package com.cloudinary.api.services;

import com.cloudinary.Uploader;
import com.cloudinary.api.repositories.FilesRepository;
import com.cloudinary.api.services.impl.FilesServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileServiceTest {
    @InjectMocks
    private FilesServiceImpl filesService;

    @Mock
    private FilesRepository filesRepository;

    @Test
    void uploadFileToCloudinaryTest() throws IOException {
        MultipartFile multipartFile=mock(MultipartFile.class);
        String data="prodID,prodName,ssid\n" +
                "1,Fan,ABCDEF1234";
        when(multipartFile.getBytes()).thenReturn(data.getBytes("UTF-8"));
        ReflectionTestUtils.setField(filesService,"apiKey","464793877497491");
        ReflectionTestUtils.setField(filesService,"cloudName","dzgnt6hpe");
        ReflectionTestUtils.setField(filesService,"apiSecret","qmogNJo0xojbvseyd-3ywhd9RB4");

        Assertions.assertThrows(RuntimeException.class,()->filesService.uploadFileToCloudinary(multipartFile));
    }
}
