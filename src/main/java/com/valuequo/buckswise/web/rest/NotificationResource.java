
package com.valuequo.buckswise.web.rest;

import java.util.ArrayList;

import com.valuequo.buckswise.service.NotificationService;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationResource {

    @Autowired
    private NotificationService notify;

    @GetMapping("/get/{uid}")
    public ArrayList<String> getNotifications(@PathVariable("uid") Long uid) throws JSONException {

        ArrayList<String> notifications = new ArrayList<>();

        String expiryMessage = notify.getPlanExpiry(uid);
        if (expiryMessage != null) {
            notifications.add(expiryMessage);
        }

        String profileMessage = notify.getProfileData(uid);
        if (profileMessage != null) {
            notifications.add(profileMessage);
        }

        return notifications;
    }
}