package com.valuequo.buckswise.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GoogledriveService {

    private final Logger log = LoggerFactory.getLogger(GoogledriveService.class);

    // private static final String APPLICATION_NAME = "buckswise";
    // private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    // private static final String CREDENTIAL_FILE_STRING = "/credentials.json";
    // private static final String TOKENS_DIRECTORY_PATH  = "src/main/resources/token";
    // private static final List<String> SCOPES = Collections.singletonList("https://www.googleapis.com/auth/drive");
    // public String upload() {

    //     try {
	// 		HttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    //         InputStream in = GoogledriveService.class.getResourceAsStream(CREDENTIAL_FILE_STRING);
    //         System.out.println("json file" + in);
    //         GoogleClientSecrets googleClientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
            
    //         GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
    //             HTTP_TRANSPORT, JSON_FACTORY, googleClientSecrets, SCOPES)
    //             .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
    //             .setAccessType("offline")
    //             .build();

    //     } catch (Exception e) {
    //         System.out.println(e);
    //     }       
    //     return null;
    // }

    

}
