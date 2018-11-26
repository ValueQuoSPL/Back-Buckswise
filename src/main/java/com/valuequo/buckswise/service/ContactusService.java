package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.Contactus;
import com.valuequo.buckswise.repository.ContactusRepository;
import com.valuequo.buckswise.service.dto.ContactusDTO;
import com.valuequo.buckswise.service.mapper.ContactusMapper;

import io.github.jhipster.config.JHipsterProperties;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.MessagingException;

import javax.mail.PasswordAuthentication;

/**
 * Service Implementation for managing Contactus.
 */
@Service
@Transactional
public class ContactusService {

	private final Logger log = LoggerFactory.getLogger(ContactusService.class);

	private final ContactusRepository contactusRepository;

	private final ContactusMapper contactusMapper;

	private final JHipsterProperties jHipsterProperties;

	public ContactusService(ContactusRepository contactusRepository, ContactusMapper contactusMapper,
			JHipsterProperties jHipsterProperties) {
		this.contactusRepository = contactusRepository;
		this.contactusMapper = contactusMapper;
		this.jHipsterProperties = jHipsterProperties;
	}

	/**
	 * Save a contactus.
	 *
	 * @param contactusDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	public ContactusDTO save(ContactusDTO contactusDTO) {
		log.debug("Request to save Contactus : {}", contactusDTO);
		Contactus contactus = contactusMapper.toEntity(contactusDTO);
		contactus = contactusRepository.save(contactus);
		return contactusMapper.toDto(contactus);
	}

	/**
	 * Get all the contactuses.
	 *
	 * @return the list of entities
	 */
	@Transactional(readOnly = true)
	public List<ContactusDTO> findAll() {
		log.debug("Request to get all Contactuses");
		return contactusRepository.findAll().stream().map(contactusMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	/**
	 * Get one contactus by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Transactional(readOnly = true)
	public ContactusDTO findOne(Long id) {
		log.debug("Request to get Contactus : {}", id);
		Contactus contactus = contactusRepository.findOne(id);
		return contactusMapper.toDto(contactus);
	}

	/**
	 * Delete the contactus by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	public void delete(Long id) {
		log.debug("Request to delete Contactus : {}", id);
		contactusRepository.delete(id);
	}

//	public void sendMail(String userMail) {
//    	String from = "admin@valuequo.com";
//    	String to = userMail;
//    	String cc = "sheetal.s@valuequo.com";
//    	
//    	String username = "buckswisemail@gmail.com";
//    	String password = "ValueQuo#1";
//    	
//    	String host = "smtp.gmail.com";
//    	
//    	Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.ssl.trust", host);
//
//    	Session session = Session.getInstance(props,
//    		      new javax.mail.Authenticator() {
//    		         protected PasswordAuthentication getPasswordAuthentication() {
//    		            return new PasswordAuthentication(username, password);
//    		         }
//    		      });
//
//	try
//
//	{
//		// Create a default MimeMessage object.
//		Message message = new MimeMessage(session);
//		
//		message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
//		// Set From: header field of the header.
//		message.setFrom(new InternetAddress(from));
//
//		// Set To: header field of the header.
//		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
//
//		// Set Subject: header field
//		message.setSubject("Gmail - Email Test");
//
//		// Now set the actual message
//		message.setText("");
//
//		// Send message
//		Transport.send(message);
//
//		System.out.println("Sent message successfully.... from GMAIL");
//
//	}catch(
//	MessagingException e)
//	{
//		throw new RuntimeException(e);
//	}
	
	}

