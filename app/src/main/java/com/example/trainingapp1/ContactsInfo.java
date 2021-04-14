package com.example.trainingapp1;

import android.media.Image;

public class ContactsInfo {
    private String contactId;
    private String displayName;
    private String phoneNumber;
   // private Image contactimage;

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

//    public Image getContactimage() {
//        return contactimage;
//    }
//
//    public void setContactimage(Image contactimage) {
//        this.contactimage = contactimage;
//    }
}
