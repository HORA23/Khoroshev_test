package com.test_Khoroshev.test_Khoroshev.controller;

import com.test_Khoroshev.test_Khoroshev.controller.dto.ContactDto;
import com.test_Khoroshev.test_Khoroshev.models.Contact;
import com.test_Khoroshev.test_Khoroshev.service.ContactService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContactsEndpointTest {
    ContactService contactService;
    private ContactEndpoint contactEndpoint;

    @BeforeEach
    private void init() {
        contactService = mock(ContactService.class);
        contactEndpoint = new ContactEndpoint(contactService);
    }

    @Test
    public void getContactsTest(){

       final List<Contact> contactsMockResponse = List.of(Contact.builder().id(1).name("Kate").build());

        when(contactService.getContacts("^A.*$")).thenReturn(contactsMockResponse);

        ResponseEntity<List<ContactDto>> responseEntity = contactEndpoint.getContacts("^A.*$");

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity);

        assertEquals(contactsMockResponse.get(0).getId(), responseEntity.getBody().get(0).getId());
        assertEquals(contactsMockResponse.get(0).getName(), responseEntity.getBody().get(0).getName());
    }
}
