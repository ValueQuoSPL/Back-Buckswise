package com.valuequo.buckswise.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Buckswise.
 * <p>
 * Properties are configured in the application.yml file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
	
	public final Cc mailcc = new Cc();
			
	public Cc getCc() {
		return mailcc;
	}
	
	public static class Cc {
		String mail;

		public String getMail() {
			return mail;
		}

		public void setMail(String mail) {
			this.mail = mail;
		}
	}
}
