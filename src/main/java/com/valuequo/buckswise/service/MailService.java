package com.valuequo.buckswise.service;

import com.valuequo.buckswise.config.ApplicationProperties;
import com.valuequo.buckswise.domain.User;
import com.valuequo.buckswise.service.dto.ContactusDTO;
import com.valuequo.buckswise.service.dto.UserDTO;

import io.github.jhipster.config.JHipsterProperties;

import org.apache.commons.lang3.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.util.Locale;

/**
 * Service for sending emails.
 * <p>
 * We use the @Async annotation to send emails asynchronously.
 */
@Service
public class MailService {

    private final Logger log = LoggerFactory.getLogger(MailService.class);

    private static final String USER = "user";
    
    private static final String CONTACT = "contact";

    private static final String USERDTO = "userDTO";
    
    private static final String FIRSTNAME = "firstName";
    
    private static final String BASE_URL = "baseUrl";

    private final JHipsterProperties jHipsterProperties;

    private final JavaMailSender javaMailSender;

    private final MessageSource messageSource;

    private final SpringTemplateEngine templateEngine;
    
    private final ApplicationProperties.Cc cc;
    
    private String email;
    
    public MailService(JHipsterProperties jHipsterProperties, JavaMailSender javaMailSender,
            MessageSource messageSource, SpringTemplateEngine templateEngine, ApplicationProperties applicationProperties) {

        this.jHipsterProperties = jHipsterProperties;
        this.javaMailSender = javaMailSender;
        this.messageSource = messageSource;
        this.templateEngine = templateEngine;
        cc = applicationProperties.getCc();
    }

    @Async
    public void sendEmail(String to, String subject,String cc,String content, boolean isMultipart, boolean isHtml) {
        log.debug("Send email[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
            isMultipart, isHtml, to, subject, content);
        // Prepare message using a Spring helper
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, CharEncoding.UTF_8);
            message.setTo(to);
            message.setCc(cc);
            message.setFrom(jHipsterProperties.getMail().getFrom());
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
            log.debug("Sent email to User '{}'", to);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.warn("Email could not be sent to user '{}'", to, e);
            } else {
                log.warn("Email could not be sent to user '{}': {}", to, e.getMessage());
            }
        }
    }

    @Async
    public void sendEmailFromTemplate(User user, String templateName, String titleKey) {
        Locale locale = Locale.forLanguageTag(user.getLangKey());
        Context context = new Context(locale);
        context.setVariable(USER, user);
        context.setVariable(BASE_URL, jHipsterProperties.getMail().getBaseUrl());
        String content = templateEngine.process(templateName, context);
        String subject = messageSource.getMessage(titleKey, null, locale);
        sendEmail(user.getEmail(), subject, "contact@valuequo.com",  content,false, true);

    }
    @Async
    public void sendEmailContactus(ContactusDTO contact, String templateName, String titleKey) {
//    	String eMailCc = this.cc.getMail();
    	try {
            Locale locale = Locale.forLanguageTag("English");
        	Context context = new Context(locale);
            context.setVariable(CONTACT, contact);
            context.setVariable(BASE_URL, jHipsterProperties.getMail().getBaseUrl());
            String content = templateEngine.process(templateName, context);
            String subject = messageSource.getMessage(titleKey, null,locale);
            String email = contact.getEmail();
            sendEmail(email, subject, this.cc.getMail(), content,false, true);

    	}
    	catch(Exception e) {
    		log.debug(e.getMessage());
    	}
    }
    
    @Async
    public void sendEmailByAdmin(UserDTO userDTO, String templateName, String titleKey) {
    	try {
            Locale locale = Locale.forLanguageTag("English");
        	Context context = new Context(locale);
            context.setVariable(USERDTO, userDTO);
            context.setVariable(BASE_URL, jHipsterProperties.getMail().getBaseUrl());
            String content = templateEngine.process(templateName, context);
            String subject = messageSource.getMessage(titleKey, null,locale);
            String email = userDTO.getEmail();
            sendEmail(email, subject, this.cc.getMail(), content,false, true);
    		
    	}
    	catch(Exception e) {
    		log.debug(e.getMessage());
    	}
    }

    @Async
    public void sendActivationEmail(User user) {
        log.debug("Sending activation email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "activationEmail", "email.activation.title");
    }

    @Async
    public void sendCreationEmail(User user) {
        log.debug("Sending creation email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "creationEmail", "email.activation.title");
    }

    @Async
    public void sendPasswordResetMail(User user) {
        log.debug("Sending password reset email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "passwordResetEmail", "email.reset.title");
    }
    @Async
    public void sendEmailContact(ContactusDTO contact) {
        log.debug("Sending contact email to '{}'", contact.getEmail());
        sendEmailContactus(contact, "contactusEmail", "email.contact.title1");
    }
    @Async
    public void sendMailByAdmin(UserDTO userDTO) {
    	if(userDTO.getMailDetail() == "registrationSupport") {
    		sendEmailByAdmin(userDTO, userDTO.getMailDetail(), "email.register.title1");    		
    	} else {
    		sendEmailByAdmin(userDTO, userDTO.getMailDetail(), "email.welcome.title1");
    	}
    }
    @Async
    public void sendReferEmail(ContactusDTO contact) {
    	sendEmailContactus(contact, "referFriendsEmail", "email.refer.title1");
    }
    
    @Async
    public void sendEmailWelcome(String firstName, String templateName, String titleKey) {
    	try {
            Locale locale = Locale.forLanguageTag("English");
        	Context context = new Context(locale);
            context.setVariable(FIRSTNAME, firstName);
            context.setVariable(BASE_URL, jHipsterProperties.getMail().getBaseUrl());
            String content = templateEngine.process(templateName, context);
            String subject = messageSource.getMessage(titleKey, null,locale);
            String email = this.email;
            sendEmail(email, subject, this.cc.getMail(), content,false, true);
    		
    	}
    	catch(Exception e) {
    		log.debug(e.getMessage());
    	}
    }
    

    @Async
    public void sendMailForWelcome(String firstName, String email) {
    	this.email = email;
    	sendEmailWelcome(firstName, "welcomeMail", "email.welcome.title1");
    }
        // familyaccess password reset mail........ added by ranjan
    @Async
    public void sendfamilyAccessEmail(User user) {
        // System.out.println("from mail service" + userDTO);
        // System.out.println("send family access works");
        log.debug("Sending password reset email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "familyAccesspasswordResetEmail", "email.familyaccessPasswordreset.title");
    }
//         end..............
}
