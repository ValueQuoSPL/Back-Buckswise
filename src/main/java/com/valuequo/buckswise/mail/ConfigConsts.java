package com.valuequo.buckswise.mail;

public final class ConfigConsts {

    // Recipient's email ID needs to be mentioned.
    public static final String TO_EMAIL_ADDRESS = "";

    // Sender's email ID needs to be mentioned
    public static final String FROM_EMAIL_ADDRESS = "noreply@buckswise.com";

    // Email address for Login
    public static final String USER_NAME_EMAIL = "pratikbalivq@gmail.com";

    // Email password for Login
    public static final String USER_PASSWORD = "pratik96";

    // Physical Folder Path
    public static final String FILE_PATH = "./hi.txt";

    // Set SMTP Server Address - GMAIL, OUTLOOK, YAHOO or ZOHO
    public static final String SMPT_HOST_ADDRESS = MailServers.GMAIL.getAddress();

    // Server name - gets reflected in email message Subject and body
    public static final String SMPT_HOST_NAME = MailServers.GMAIL.getName();

}
