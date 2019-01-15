package com.valuequo.buckswise.web.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valuequo.buckswise.service.FileReadService;

@RestController
@RequestMapping("/api")
public class FileRead {
	private final FileReadService FileReadService;
	
public FileRead(FileReadService FileReadService) {
	this.FileReadService = FileReadService;
}

@PostMapping("/readfile")
public void  readData() {
	FileReadService.readData();
}
}
