package com.valuequo.buckswise.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FileReadService {
	public void readData() {
		System.out.println("in file read service");
	}

}
