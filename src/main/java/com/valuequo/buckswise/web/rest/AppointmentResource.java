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
import com.valuequo.buckswise.service.AppointmentService;
import com.valuequo.buckswise.web.rest.errors.BadRequestAlertException;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;
import com.valuequo.buckswise.service.dto.AppointmentDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

/**
 * REST controller for managing Appointment.
 */
@RestController
@RequestMapping("/api")
public class AppointmentResource {

    private final Logger log = LoggerFactory.getLogger(AppointmentResource.class);

    private static final String ENTITY_NAME = "appointment";

    private final AppointmentService appointmentService;

	private static AppointmentDTO appointmentDTO;
    
    private static final String APPLICATION_NAME = "buckswise";
	private static com.google.api.services.calendar.Calendar service;
	private static final String SERVICE_ACCOUNT_EMAIL = "admin-606@buckswise-219810.iam.gserviceaccount.com";
	private static final String SERVICE_ACCOUNT_PKCS12_FILE = "src/main/resources/buckswise-219810-0fd66feedbb7.p12";
	private static final String userEmailId = "admin@valuequo.com";

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
    
    @PostMapping("/appointments")
    @Timed
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDTO) throws URISyntaxException, GeneralSecurityException, IOException {
        log.debug("REST request to save Appointment : {}", appointmentDTO);
        dateTime = appointmentDTO.getDate();
        if (appointmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new appointment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        try {
			HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			JsonFactory jsonFactory = new JacksonFactory();
			GoogleCredential gCred = new GoogleCredential.Builder().setTransport(httpTransport)
					.setJsonFactory(jsonFactory)
					.setServiceAccountId(SERVICE_ACCOUNT_EMAIL)
					.setServiceAccountScopes(Collections.singleton("https://www.googleapis.com/auth/calendar"))
					.setServiceAccountUser(userEmailId)
					.setServiceAccountPrivateKeyFromP12File(new java.io.File(SERVICE_ACCOUNT_PKCS12_FILE))
					.build();
			service = new com.google.api.services.calendar.Calendar.Builder(httpTransport, jsonFactory, gCred)
					.setApplicationName(APPLICATION_NAME).build();
			Events events = service.events();
			Calendar calendar = addCalendar();
			addEvent(calendar);
		} catch (Exception e) {
			
		}
        AppointmentDTO result = appointmentService.save(appointmentDTO);
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

	private static Event newEvent() {
		 Event event = new Event();
	        event.setSummary("Discussion on Plan");
	        DateTime startDateTime = new DateTime(dateTime);
	        EventDateTime start = new EventDateTime()
	            .setDateTime(startDateTime)
	            .setTimeZone("Asia/Calcutta");
	        event.setStart(start);

	        DateTime endDateTime = new DateTime(dateTime);
	        EventDateTime end = new EventDateTime()
	            .setDateTime(endDateTime)
	            .setTimeZone("Asia/Calcutta");
	        event.setEnd(end);
	        EventAttendee[] eventAttendee = new EventAttendee[] {
	        		new EventAttendee().setEmail("sandeep.pote@valuequo.com"),
	        		new EventAttendee().setEmail("contact@valuequo.com")
	        };
	        event.setAttendees(Arrays.asList(eventAttendee));
	        EventReminder[] reminderOverrides = new EventReminder[] {
	        	    new EventReminder().setMethod("email").setMinutes(01),
	        	    new EventReminder().setMethod("popup").setMinutes(10),
	        };
	        Event.Reminders reminders = new Event.Reminders()
	        	    .setUseDefault(false)
	        	    .setOverrides(Arrays.asList(reminderOverrides));
	        event.setReminders(reminders);
	        return event;
	}

	private static void addEvent(Calendar calendar) throws IOException {
		Event event = newEvent();
		Event result = service.events().insert(calendar.getId(), event).setSendNotifications(true).execute();
	}

	private static Calendar addCalendar() throws IOException {
		Calendar entry = new Calendar();
		entry.setSummary("today meeting 22-10");
		return service.calendars().insert(entry).execute();

	}
    
}
