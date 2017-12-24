package com.baeldung.reactiveAPI;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Foo {

    private final long id;
    private final String name;
}
