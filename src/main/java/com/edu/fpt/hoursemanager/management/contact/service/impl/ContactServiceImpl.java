package com.edu.fpt.hoursemanager.management.contact.service.impl;

import com.edu.fpt.hoursemanager.common.entity.AccountLoginCommon;
import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.common.utils.Utils;
import com.edu.fpt.hoursemanager.management.account.repository.AccountRepository;
import com.edu.fpt.hoursemanager.management.contact.entity.Contact;
import com.edu.fpt.hoursemanager.management.contact.model.request.ContactRequest;
import com.edu.fpt.hoursemanager.management.contact.model.response.ContactResponse;
import com.edu.fpt.hoursemanager.management.contact.model.response.ContactRoleResponse;
import com.edu.fpt.hoursemanager.management.contact.repository.ContactRepository;
import com.edu.fpt.hoursemanager.management.contact.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@Slf4j
public class ContactServiceImpl implements ContactService {
    @Autowired
    ContactRepository contactRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public ResponseEntity<ResponseModels> getCurrentAccountContact() {
        List<ContactRoleResponse> contactRoleResponses = null;
        ContactResponse contactResponse = new ContactResponse();
        try {
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            if (accountLoginCommon.getUserName() != null) {
                contactRoleResponses = contactRepository.getContactWithRole(accountLoginCommon.getUserName());
                contactResponse.setId(contactRoleResponses.get(0).getId());
                contactResponse.setFullName(contactRoleResponses.get(0).getFullName());
                contactResponse.setPhone(contactRoleResponses.get(0).getPhone());
                contactResponse.setDob(Utils.covertDateToString(contactRoleResponses.get(0).getDob()));
                contactResponse.setGender(contactRoleResponses.get(0).getGender());
                contactResponse.setAddress(contactRoleResponses.get(0).getAddress());
                contactResponse.setImageAfter(contactRoleResponses.get(0).getImageAfter());
                contactResponse.setImageBefore(contactRoleResponses.get(0).getImageBefore());
                contactResponse.setNumberId(contactRoleResponses.get(0).getNumberId());
                contactResponse.setEmailAccount(accountLoginCommon.getUserName());
                Set<String> roleSet = new HashSet<>();
                for (ContactRoleResponse contact : contactRoleResponses) {
                    roleSet.add(contact.getRole());
                }
                contactResponse.setRole(roleSet);
            } else {
                return ResponseModels.unauthorized();
            }
        } catch (Exception e) {
            return ResponseModels.error("Contact Service Error : " + e.getMessage());
        }
        return ResponseModels.success(contactResponse);
    }

    @Override
    public ResponseEntity<ResponseModels> getContactAndAccountByEmail(String email) {
        return ResponseModels.success(contactRepository.getAccountAndContactByEmail(email));
    }

    @Override
    public ResponseEntity<ResponseModels> deleteContact(Long id) {
        Contact contact = contactRepository.getById(id);
        contact.setDeleted(true);
        return ResponseModels.success(contactRepository.save(contact));
    }

    @Override
    public ResponseEntity<ResponseModels> addContact(ContactRequest contactRequest) {
        Contact contact = new Contact();
        AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
        try {
            contact.setFullName(contactRequest.getFullName());
            contact.setPhone(contactRequest.getPhone());
            contact.setDob(Utils.covertStringToDate(contactRequest.getDob()));
            contact.setGender(Boolean.valueOf(contactRequest.getGender()));
            contact.setAddress(contactRequest.getAddress());
            contact.setType(contactRequest.getType());
            contact.setCreatedBy(accountLoginCommon.getUserName());
            contact.setImageAfter(contactRequest.getImageAfter());
            contact.setNumberId(contactRequest.getNumberId());
            contact.setImageBefore(contactRequest.getImageBefore());
        } catch (Exception e) {
            return ResponseModels.error(e.getMessage());
        }
        return ResponseModels.success(contactRepository.save(contact));
    }

    @Override
    public ResponseEntity<ResponseModels> editContact(ContactRequest request) {
        Contact contact = null;
        try {
            if (request != null) {
                AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
                contact = contactRepository.getAccountContactByEmail(accountLoginCommon.getUserName());
                contact.setPhone(request.getPhone());
                contact.setGender(Boolean.valueOf(request.getGender()));
                contact.setDob(Utils.covertStringToDate(request.getDob()));
                contact.setAddress(request.getAddress());
                contact.setFullName(request.getFullName());
                contact.setType(request.getType());
                contact.setNumberId(request.getNumberId());
                contact.setImageBefore(request.getImageBefore());
                contact.setImageAfter(request.getImageAfter());
                contactRepository.save(contact);
            }
        } catch (Exception e) {
            return ResponseModels.error("Contact Service Error : " + e.getMessage());
        }
        return ResponseModels.success("Edit Contact Success.");
    }

    @Override
    public Contact getAccountContactByEmail(String email) {
        Contact contact = contactRepository.getAccountContactByEmail(email);
        return contact;
    }
}
