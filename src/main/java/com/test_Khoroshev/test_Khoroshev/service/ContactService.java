package com.test_Khoroshev.test_Khoroshev.service;

import com.test_Khoroshev.test_Khoroshev.models.Contact;
import java.util.List;

public interface ContactService {

    List<Contact> getContacts(String regex);
}
