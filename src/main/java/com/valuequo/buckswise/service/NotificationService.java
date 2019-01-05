package com.valuequo.buckswise.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.valuequo.buckswise.domain.Familyprofile;
import com.valuequo.buckswise.domain.MyProfile;
import com.valuequo.buckswise.domain.Userplan;
import com.valuequo.buckswise.repository.FamilyprofileRepository;
import com.valuequo.buckswise.repository.MyProfileRepository;
import com.valuequo.buckswise.repository.UserRepository;
import com.valuequo.buckswise.repository.UserplanRepository;

import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * NotificationService
 */

 @Service
public class NotificationService {

    @Autowired
    private UserplanRepository userplan;

    @Autowired
    private FamilyprofileRepository familyProfile;
    @Autowired
    private MyProfileRepository myProfile;

    Date systemDate = new Date();
    LocalDate dbDate;
    String expiryMessage = null;
    String profileMessage = null;
    public String getPlanExpiry(Long uid) {

        List<Userplan> user = userplan.findByUid(uid);

        for(Userplan userplan: user)
        {
            this.dbDate = userplan.getExpiryDate();
        }
        LocalDate currentDate = this.systemDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long daysBetween = ChronoUnit.DAYS.between(currentDate, dbDate);

        if (daysBetween <= 15 && daysBetween > 0) {
            expiryMessage = "Your plan will be expired in "+ daysBetween +" days";
        } else {
            expiryMessage = null;
        }
        return expiryMessage;
    }

    public String getProfileData(Long uid) {

        List<Familyprofile> familyProfileData = familyProfile.findByUid(uid);
        List<MyProfile> myProfileData = myProfile.findByUid(uid);

        if (familyProfileData != null || myProfileData != null) {
            profileMessage = "Kindly complete your profile";
        } else {
            profileMessage = null;
        }
        return profileMessage;
    }


}