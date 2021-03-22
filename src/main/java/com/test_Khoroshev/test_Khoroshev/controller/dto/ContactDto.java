package com.test_Khoroshev.test_Khoroshev.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class ContactDto {
    @NotNull
    private int id;
    private String name;
}
