package com.test_Khoroshev.test_Khoroshev.service.impl;

import com.test_Khoroshev.test_Khoroshev.dao.ContactDao;
import com.test_Khoroshev.test_Khoroshev.models.Contact;
import com.test_Khoroshev.test_Khoroshev.service.ContactService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {

    ContactDao contactDao;

    @Override
    public List<Contact> getContacts(String regex) {
        return filterContacts(contactDao.getContacts(),regex);
    }

    private List<Contact> filterContacts(List<Contact> contacts, String regex) {
        return contacts.parallelStream()
                .filter(contact -> !contact.getName().matches(regex.trim()))
                .collect(Collectors.toList());
    }
}
