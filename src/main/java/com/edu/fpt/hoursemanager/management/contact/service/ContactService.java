package com.edu.fpt.hoursemanager.management.contact.service;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.contact.entity.Contact;
import com.edu.fpt.hoursemanager.management.contact.model.request.ContactRequest;
import org.springframework.http.ResponseEntity;

public interface ContactService {
    ResponseEntity<ResponseModels> addContact(ContactRequest contactRequest);

    ResponseEntity<ResponseModels> editContact(ContactRequest request);

    Contact getAccountContactByEmail(String email);

    ResponseEntity<ResponseModels> getCurrentAccountContact();

    ResponseEntity<ResponseModels> getContactAndAccountByEmail(String email);
}
