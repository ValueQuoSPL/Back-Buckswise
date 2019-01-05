package com.valuequo.buckswise.web.rest;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.codahale.metrics.annotation.Timed;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.valuequo.buckswise.domain.Googledrive;
import com.valuequo.buckswise.service.GoogledriveService;
import com.valuequo.buckswise.service.dto.GoogledriveDTO;
import com.valuequo.buckswise.service.dto.UrlDTO;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.Drive.Files.Delete;
import com.google.api.services.drive.model.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Author : Sandeep Pote GoogleDrive controller upload file code
 */

@RestController
@RequestMapping("/api/google-drive")
public class GoogleDriveResource {

    private final Logger log = LoggerFactory.getLogger(GoogleDriveResource.class);

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private GoogleClientSecrets clientSecrets;

    Credential credential;

    GoogleAuthorizationCodeFlow flow;

    private static final String APPLICATION_NAME = "BucksWise";

    HttpTransport httpTransport = new NetHttpTransport();

    private String redirectURI = "https://www.buckswise.com:8443/api/google-drive/drive";

    static List<String> SCOPES = Arrays.asList("https://www.googleapis.com/auth/drive");

    private static com.google.api.services.drive.Drive client;

    String clientId = "504894412631-6tn5bces6bp23graqhssqppcqvqrql6d.apps.googleusercontent.com";
    String clientSecret = "gQg9u9QAXYzJcYDNhHRhhZQi";

    @Autowired
    private GoogledriveService googledrive;

    @Autowired
    private GoogledriveDTO googledriveDTO;

    @Autowired
    private UrlDTO url;

    private java.io.File files;
    private String mediatype;
    private String fileName;

    @PostMapping("/call/{tid}/{uid}/{type}/{fileName}")
    @CrossOrigin(origins = "https://www.buckswise.com")
    public UrlDTO googleConnectionStatus(@RequestParam("file") MultipartFile file, @PathVariable("tid") Long tid, @PathVariable("uid") Long uid, @PathVariable("type") String type, @PathVariable("fileName") String fileName) throws Exception {
        googledriveDTO.setTid(tid);
        googledriveDTO.setUid(uid);
        googledriveDTO.setType(type);
        googledriveDTO.setFilename(fileName);
        this.mediatype = file.getContentType();
        this.fileName = file.getOriginalFilename();
        this.files = googledrive.convert(file);
        String uri = authorize();
        url.setUrl(uri);
        return url;
    }

    @GetMapping("/drive")
    public String upload(@RequestParam(value = "code") String code) throws URISyntaxException {
        try {
            TokenResponse response = flow.newTokenRequest(code).setRedirectUri(redirectURI).execute();
            credential = flow.createAndStoreCredential(response, "userID");
            client = new com.google.api.services.drive.Drive.Builder(httpTransport, JSON_FACTORY, credential)
                    .setApplicationName(APPLICATION_NAME).build();

            // upload file code
            File fileMetadata = new File();
            fileMetadata.setName(this.fileName);
            FileContent mediaContent = new FileContent(this.mediatype, this.files);
            File file = client.files().create(fileMetadata, mediaContent).setFields("id").execute();

            // view the uploaded documents
            File filesView = client.files().get(file.getId()).setFields("webViewLink").execute();
            String fileView = filesView.getWebViewLink();
            // ------> Delete File from drive
            Delete filesDelete = client.files().delete(file.getId()); 
            String deleteId = filesDelete.getFileId();
            googledriveDTO.setFileweblink(fileView);
            googledriveDTO.setDeleteweblink(deleteId);
            googledrive.saveFileData(googledriveDTO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "<div style='color:#f1592a; text-align: center; position: absolute; top:20%; left:0; bottom: 0; right: 0;'><h4>Your document uploaded successfully.</h4></div>";
    }

    private String authorize() throws Exception {
        AuthorizationCodeRequestUrl authorizationUrl;
        if (flow == null) {
            Details web = new Details();
            web.setClientId(clientId);
            web.setClientSecret(clientSecret);
            clientSecrets = new GoogleClientSecrets().setWeb(web);
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets,
                    Collections.singleton(DriveScopes.DRIVE)).build();
        }
        authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(redirectURI);
        return authorizationUrl.build();
    }

    @GetMapping("/drive/{uid}")
    public List<Googledrive> getFiles(@PathVariable("uid") Long uid) {
        return googledrive.getFile(uid);
    }

    @DeleteMapping("/drive/{id}")
    @Timed
    public void delete(@PathVariable("id") Long id) {
        googledrive.deleteFile(id);
    }
}
