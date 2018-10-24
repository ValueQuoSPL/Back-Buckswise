package com.valuequo.buckswise.web.rest.vm;

import com.valuequo.buckswise.service.dto.UserDTO;
import javax.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * View Model extending the UserDTO, which is meant to be used in the user management UI.
 */
public class ManagedUserVM extends UserDTO {

    public static final int PASSWORD_MIN_LENGTH = 4;
    private final Logger log = LoggerFactory.getLogger(ManagedUserVM.class);
    public static final int PASSWORD_MAX_LENGTH = 100;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;
    private String gcaptcha;
    private boolean success;

    public ManagedUserVM() {
        // Empty constructor needed for Jackson.
    }
    
    public String getGcaptcha() {
		return gcaptcha;
	}

	public void setGcaptcha(String gcaptcha) {
		this.gcaptcha = gcaptcha;
	}

	public boolean isSuccess() {
        log.debug("success " + success);
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ManagedUserVM{" +
            "} " + super.toString();
    }
}
