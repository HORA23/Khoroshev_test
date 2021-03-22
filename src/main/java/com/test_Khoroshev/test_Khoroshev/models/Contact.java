package com.test_Khoroshev.test_Khoroshev.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class Contact {

    @NotNull
    private final int id;
    private final String name;

}
