package com.valuequo.buckswise.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar.Events;
import com.google.api.services.calendar.model.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import com.valuequo.buckswise.domain.Appointment;
import com.valuequo.buckswise.domain.User;
import com.valuequo.buckswise.repository.AppointmentRepository;
import com.valuequo.buckswise.repository.UserRepository;
import com.valuequo.buckswise.service.AppointmentService;
import com.valuequo.buckswise.web.rest.errors.BadRequestAlertException;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;
import com.valuequo.buckswise.web.rest.vm.ManagedUserVM;
import com.valuequo.buckswise.service.dto.AppointmentDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Author: Sandeep Pote
 * REST controller for managing Appointment.
 */
@RestController
@RequestMapping("/api")
public class AppointmentResource {

    private final Logger log = LoggerFactory.getLogger(AppointmentResource.class);

    private static final String ENTITY_NAME = "appointment";

    private final AppointmentService appointmentService;

    @Autowired
	private UserRepository userRepository;
    private ManagedUserVM managedUserVM;
    
    @Autowired
    private AppointmentRepository appointmentRepository;
	

    public AppointmentResource(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    /**
     * POST  /appointments : Create a new appointment.
     *
     * @param appointmentDTO the appointmentDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new appointmentDTO, or with status 400 (Bad Request) if the appointment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws IOException 
     * @throws GeneralSecurityException 
     */
    
    private static String dateTime;
    private static String userEmail;
    
    @PostMapping("/appointments")
    @Timed
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDTO) throws URISyntaxException, GeneralSecurityException, IOException {
        log.debug("REST request to save Appointment : {}", appointmentDTO);
        dateTime = appointmentDTO.getDate();
        List<User> userDetails = userRepository.findById(appointmentDTO.getUid());
        for(User email: userDetails) {
        	userEmail = email.getEmail();
        }
        appointmentService.createCalendar(dateTime, userEmail);  
        AppointmentDTO result = appointmentService.save(appointmentDTO);
        if (appointmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new appointment cannot already have an ID", ENTITY_NAME, "idexists");
        }

        return ResponseEntity.created(new URI("/api/appointments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /appointments : Updates an existing appointment.
     *
     * @param appointmentDTO the appointmentDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated appointmentDTO,
     * or with status 400 (Bad Request) if the appointmentDTO is not valid,
     * or with status 500 (Internal Server Error) if the appointmentDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws IOException 
     * @throws GeneralSecurityException 
     */
    @PutMapping("/appointments")
    @Timed
    public ResponseEntity<AppointmentDTO> updateAppointment(@RequestBody AppointmentDTO appointmentDTO) throws URISyntaxException, GeneralSecurityException, IOException{
        log.debug("REST request to update Appointment : {}", appointmentDTO);
        if (appointmentDTO.getId() == null) {
            return createAppointment(appointmentDTO);
        }
        AppointmentDTO result = appointmentService.save(appointmentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, appointmentDTO.getId().toString()))
            .body(result);
    }
    
    // updating the status of appointment //
    
    private String status;
    private Long id;
    
    @PutMapping("/updateAppointment")
    @Timed
    public String updateAppointment(@RequestBody Map<String, Object> data) throws JSONException {
    	System.out.println("updateAppointment" + data);
    	for (Map.Entry<String, Object> entry : data.entrySet()) {
    		JSONObject jObj = new JSONObject(data);
    		this.status = jObj.get("status").toString();
    		this.id = jObj.getLong("id");
    	}
    	appointmentService.updateBookAppoint(this.status, this.id);
    	return null;
    }

    /**
     * GET  /appointments : get all the appointments.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of appointments in body
     */
    @GetMapping("/appointments")
    @Timed
    public List<AppointmentDTO> getAllAppointments() {
        log.debug("REST request to get all Appointments");
        return appointmentService.findAll();
        }

    /**
     * GET  /appointments/:id : get the "id" appointment.
     *
     * @param id the id of the appointmentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the appointmentDTO, or with status 404 (Not Found)
     */
    @GetMapping("/appointments/{id}")
    @Timed
    public ResponseEntity<AppointmentDTO> getAppointment(@PathVariable Long id) {
        log.debug("REST request to get Appointment : {}", id);
        AppointmentDTO appointmentDTO = appointmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(appointmentDTO));
    }

    @GetMapping("/appointmentByUid/{uid}")
    public List<Appointment> getData(@PathVariable Long uid) {
    	return appointmentService.getUserData(uid);
    }
     
    /**
     * DELETE  /appointments/:id : delete the "id" appointment.
     *
     * @param id the id of the appointmentDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/appointments/{id}")
    @Timed
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        log.debug("REST request to delete Appointment : {}", id);
        appointmentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
