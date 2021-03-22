package com.test_Khoroshev.test_Khoroshev.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.test_Khoroshev.test_Khoroshev.dao.ContactDao;
import com.test_Khoroshev.test_Khoroshev.models.Contact;
import com.test_Khoroshev.test_Khoroshev.service.impl.ContactServiceImpl;

import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTest {

    private Contact contactMock;

    @InjectMocks
    ContactServiceImpl userService;

    @Mock
    ContactDao contactDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        contactMock = Contact.builder()
                .id(1)
                .name("fullName")
                .build();
    }

    @Test
    public void getContactsTest() {
        Mockito.when(contactDao.getContacts()).thenReturn(List.of(contactMock));

        List<Contact> actualContact = userService.getContacts("^A.*$");

        assertNotNull(actualContact);

        assertEquals(1, actualContact.get(0).getId());
        assertEquals("fullName", actualContact.get(0).getName());
    }

    @Test
    public void getContactsTestWithEmptyResults() {
        Mockito.when(contactDao.getContacts()).thenReturn(Collections.emptyList());

        List<Contact> actualContact = userService.getContacts("^A.*$");

        assertNotNull(actualContact);
        assertEquals(0, actualContact.size());
    }

}
