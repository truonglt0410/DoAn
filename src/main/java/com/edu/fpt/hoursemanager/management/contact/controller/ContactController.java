package com.edu.fpt.hoursemanager.management.contact.controller;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.account.service.AccountService;
import com.edu.fpt.hoursemanager.management.contact.model.request.ContactRequest;
import com.edu.fpt.hoursemanager.management.contact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private ContactService contactService;

    @GetMapping(value = "/get-contact")
    public ResponseEntity<ResponseModels> getCurrentAccountContact(){
        return contactService.getCurrentAccountContact();
    }

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseModels> addContact(@RequestBody ContactRequest request){
        return contactService.addContact(request);
    }

    @PostMapping(value = "/edit")
    public ResponseEntity<ResponseModels> editContact(@RequestBody ContactRequest request){
        return contactService.editContact(request);
    }

    @GetMapping(value = "/get-all-contact")
    public ResponseEntity<ResponseModels> getAccountAndContactByEmail(@RequestParam String email){
        return contactService.getContactAndAccountByEmail(email);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<ResponseModels> deleteContact(@RequestParam Long id){
        return contactService.deleteContact(id);
    }
}
