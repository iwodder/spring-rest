package com.example.take.some.rest.springrest.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SomeBean {

    List<String> vals;
    String string1;
    String string2;

    @JsonIgnore
    String string3;

    public SomeBean(String string1, String string2, String string3) {
        this.string1 = string1;
        this.string2 = string2;
        this.string3 = string3;
    }

    public SomeBean(String ... strings) {
        vals = new ArrayList<>(Arrays.asList(strings));
    }

    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public String getString2() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2 = string2;
    }

    public String getString3() {
        return string3;
    }

    public void setString3(String string3) {
        this.string3 = string3;
    }
}
