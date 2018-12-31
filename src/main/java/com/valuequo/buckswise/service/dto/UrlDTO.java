package com.valuequo.buckswise.service.dto;


import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class UrlDTO implements Serializable {

    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
		return url;
	}
} 