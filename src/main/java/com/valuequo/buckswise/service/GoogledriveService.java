package com.valuequo.buckswise.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.google.api.services.drive.Drive.Files.Delete;
import com.google.api.services.drive.model.File;
import com.valuequo.buckswise.domain.Googledrive;
import com.valuequo.buckswise.repository.GoogledriveRepository;
import com.valuequo.buckswise.service.dto.GoogledriveDTO;
import com.valuequo.buckswise.service.mapper.GoogledriveMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class GoogledriveService {

    private final Logger log = LoggerFactory.getLogger(GoogledriveService.class);

    private final GoogledriveMapper googledriveMapper;
    private final GoogledriveRepository googledriveRepository;

    public GoogledriveService(GoogledriveMapper googledriveMapper, GoogledriveRepository googledriveRepository) {
        this.googledriveMapper = googledriveMapper;
        this.googledriveRepository = googledriveRepository;
    }

    public java.io.File convert(MultipartFile file) throws IOException {
        java.io.File convFile = new java.io.File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile); 
        fos.write(file.getBytes());
        fos.close();
        return convFile;

    }

	public void saveFileData(Long id, String type, File filesView, Delete filesDelete) {
	}

	public void saveFileData(GoogledriveDTO googledriveDTO) {
        System.out.println("service dto data" + googledriveDTO);
        Googledrive googledrive = googledriveMapper.toEntity(googledriveDTO);
        googledriveRepository.save(googledrive);
	}

	public List<Googledrive> getFile(Long uid) {
		return googledriveRepository.findByUid(uid);
	}
}
