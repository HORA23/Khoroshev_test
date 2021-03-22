package com.test_Khoroshev.test_Khoroshev.controller;

import com.test_Khoroshev.test_Khoroshev.controller.dto.ContactDto;
import com.test_Khoroshev.test_Khoroshev.models.Contact;
import com.test_Khoroshev.test_Khoroshev.service.ContactService;
import java.util.List;
import java.util.stream.Collectors;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.test_Khoroshev.test_Khoroshev.controller.HttpMessage.*;

@RequestMapping("/")
@RestController
@Validated
@AllArgsConstructor
public class ContactEndpoint {

    ContactService contactService;

    @ApiOperation(value = "Get all contacts")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS),
            @ApiResponse(code = 404, message = NOT_FOUND),
            @ApiResponse(code = 400, message = BAD_REQUEST),
            @ApiResponse(code = 500, message = UNEXPECTED_ERROR),
            @ApiResponse(code = 504, message = TIMEOUT)
    })
    @GetMapping(value = "/hello/contacts")
    public ResponseEntity<List<ContactDto>> getContacts(@RequestParam String nameFilter) {
        List<Contact> contact = contactService.getContacts(nameFilter);
        return ResponseEntity.ok(contact.parallelStream()
                .map(this::toDto)
                .collect(Collectors.toList()));
    }

    private Contact fromDto(ContactDto userDto) {
        return Contact.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .build();
    }

    private ContactDto toDto(Contact user) {
        return ContactDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}
