package com.valuequo.buckswise.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar.Events;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import com.valuequo.buckswise.domain.Appointment;
import com.valuequo.buckswise.repository.AppointmentRepository;
import com.valuequo.buckswise.service.dto.AppointmentDTO;
import com.valuequo.buckswise.service.mapper.AppointmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Service Implementation for managing Appointment.
 */
@Service
@Transactional
public class AppointmentService {

    private final Logger log = LoggerFactory.getLogger(AppointmentService.class);

    private final AppointmentRepository appointmentRepository;

    private final AppointmentMapper appointmentMapper;
    
    private static com.google.api.services.calendar.Calendar service;
	private static final String SERVICE_ACCOUNT_EMAIL = "admin-606@buckswise-219810.iam.gserviceaccount.com";
	private static final String SERVICE_ACCOUNT_PKCS12_FILE = "buckswise-219810-0fd66feedbb7.p12";
	private static final String userEmailId = "admin@valuequo.com";
	private static final String APPLICATION_NAME = "buckswise";
	private static String dateandTime;
    private static String userMail;
    private static String hangout;

    public AppointmentService(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }

    /**
     * Save a appointment.
     *
     * @param appointmentDTO the entity to save
     * @return the persisted entity
     */
    public AppointmentDTO save(AppointmentDTO appointmentDTO) {
        log.debug("Request to save Appointment : {}", appointmentDTO);
        Appointment appointment = appointmentMapper.toEntity(appointmentDTO);
        appointment = appointmentRepository.save(appointment);
        appointment.setHangoutlink(hangout);
        return appointmentMapper.toDto(appointment);
    }

    /**
     * Get all the appointments.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<AppointmentDTO> findAll() {
        log.debug("Request to get all Appointments");
        return appointmentRepository.findAll().stream()
            .map(appointmentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one appointment by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public AppointmentDTO findOne(Long id) {
        log.debug("Request to get Appointment : {}", id);
        Appointment appointment = appointmentRepository.findOne(id);
        return appointmentMapper.toDto(appointment);
    }

    /**
     * Delete the appointment by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Appointment : {}", id);
        appointmentRepository.delete(id);
    }

	public List<Appointment> getUserData(Long uid) {
		return appointmentRepository.findByUid(uid);
	}

	public void updateBookAppoint(String status, Long id) {
		List<Appointment> result = appointmentRepository.findById(id);
		for(Appointment res: result) {
			res.setStatus(status);
		}
	}
	
	public void createCalendar(String dateTime, String userEmail) {
		dateandTime = dateTime;
		userMail = userEmail;
		
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
			addEvent();
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		
	}
	private static Event newEvent() {
		 Event event = new Event();
	        event.setSummary("Meeting With ValueQuo Financial Advisor");
	        DateTime startDateTime = new DateTime(dateandTime);
	        EventDateTime start = new EventDateTime()
	            .setDateTime(startDateTime)
	            .setTimeZone("Asia/Calcutta");
	        event.setStart(start);

	        DateTime endDateTime = new DateTime(dateandTime);
	        EventDateTime end = new EventDateTime()
	            .setDateTime(endDateTime)
	            .setTimeZone("Asia/Calcutta");
	        event.setEnd(end);
	        EventAttendee[] eventAttendee = new EventAttendee[] {
	        		new EventAttendee().setEmail(userMail)
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

	private static void addEvent() throws IOException {
		Event event = newEvent();
		String calendarId = "primary";
		Event result = service.events().insert(calendarId, event).setSendNotifications(true).execute();
		hangout = result.getHangoutLink();
	}
}
