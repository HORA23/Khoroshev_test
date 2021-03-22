package com.test_Khoroshev.test_Khoroshev.dao.impl;

import com.test_Khoroshev.test_Khoroshev.dao.ContactDao;
import com.test_Khoroshev.test_Khoroshev.models.Contact;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
@AllArgsConstructor
public class ContactDaoImpl implements ContactDao {

    JdbcTemplate jdbcTemplate;

    private static final String GET_USER_QUERY = "SELECT * FROM test_khoroshev.\"Contacts\"";

    @Override
    public List<Contact> getContacts() {
        return jdbcTemplate.query(GET_USER_QUERY, new ContactRowMapper());
    }

    static class ContactRowMapper implements RowMapper<Contact>{

        @Override
        public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Contact.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .build();
        }
    }
}
