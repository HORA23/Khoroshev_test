package com.test_Khoroshev.test_Khoroshev.dao;

import com.test_Khoroshev.test_Khoroshev.controller.ContactEndpoint;
import com.test_Khoroshev.test_Khoroshev.dao.impl.ContactDaoImpl;
import com.test_Khoroshev.test_Khoroshev.models.Contact;
import com.test_Khoroshev.test_Khoroshev.service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.sql.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContactsDaoTest {

    JdbcTemplate jdbcTemplate;

    private Contact contactMock;

    @BeforeEach
    private void init() {
        jdbcTemplate = mock(JdbcTemplate.class);
        contactMock = Contact.builder()
                .id(1)
                .name("fullName")
                .build();
    }

    @Test
    void getContactsTest() {
        ContactDao contactDao = new ContactDaoImpl(jdbcTemplate);
        //ReflectionTestUtils.setField(contactDao, "jdbcTemplate", jdbcTemplate);
        when(jdbcTemplate.query(anyString(), eq(new ContactRowMapper()))).thenReturn(List.of(contactMock));

        List<Contact> actualResult = contactDao.getContacts();
        assertEquals(0, actualResult.size());
    }

    static class ContactRowMapper implements RowMapper<Contact> {

        @Override
        public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Contact.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .build();
        }
    }
}
