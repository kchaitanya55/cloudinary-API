package com.cloudinary.api.controller;

import com.cloudinary.api.contoller.CloudinaryController;
import com.cloudinary.api.model.Response;
import com.cloudinary.api.repositories.FilesRepository;
import com.cloudinary.api.services.FilesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class CloudinaryControllerTest {
    private MockMvc mockMvc;

    @Autowired
    @InjectMocks
    private CloudinaryController cloudinaryController;

    @MockBean
    private FilesService filesService;

    @MockBean
    private FilesRepository filesRepository;

    @Test
    void integrationTestUpload() throws Exception {
        MockMultipartFile image = new MockMultipartFile("test.png", "test.png", "image/png", "prodID,prodName,ssid\n1,Fan,ABCDEF1234\n1,Cooler,ABCDEF1235".getBytes());
        when(filesService.uploadFileToCloudinary(image)).thenReturn(new ResponseEntity(new Response(), HttpStatus.OK));
        mockMvc= MockMvcBuilders.standaloneSetup(cloudinaryController).build();
        mockMvc.perform( MockMvcRequestBuilders
                        .multipart("/upload")
                        .file("file",image.getBytes())
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void getAllFilesTest() throws Exception {
        mockMvc= MockMvcBuilders.standaloneSetup(cloudinaryController).build();
        when(filesRepository.findAll()).thenReturn(new ArrayList<>());
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/images")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
