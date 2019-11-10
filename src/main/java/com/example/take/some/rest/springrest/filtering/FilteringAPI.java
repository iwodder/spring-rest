package com.example.take.some.rest.springrest.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringAPI {


    @GetMapping("/filtering")
    public SomeBean retrieveSomeBean() {
        return new SomeBean("val1", "val2", "val3");
    }

    //Query Param versioning
    @GetMapping(value="/versioning/param", params="v1")
    public PersonV1 personV1() {
        return new PersonV1("Cheyenne Nobrega");
    }

    @GetMapping(value="/versioning/param", params="v2")
    public PersonV2 personV2() {
        return new PersonV2(new Name("Ian", "Wodder"));
    }

    //Path param versioning
    @GetMapping("/versioning/v1/")
    public PersonV1 pathV1() {
        return new PersonV1("Cheyenne Nobrega");
    }

    @GetMapping("/versioning/v2/")
    public PersonV2 pathV2() {
        return new PersonV2(new Name("Ian", "Wodder"));
    }

    //Content accept versioning
    @GetMapping(value ="/versioning/produces", produces = "application/com.org.net+v1-json")
    public PersonV1 contentV1() {
        return new PersonV1("Cheyenne Nobrega");
    }

    @GetMapping(value ="/versioning/produces", produces = "application/com.org.net+v2-json")
    public PersonV2 contentV2() {
        return new PersonV2(new Name("Ian", "Wodder"));
    }

    //Header versioning
    @GetMapping(value ="/versioning/headers", headers = "X-API-VER=1")
    public PersonV1 headerV1() {
        return new PersonV1("Cheyenne Nobrega");
    }

    @GetMapping(value ="/versioning/headers", headers = "X-API-VER=2")
    public PersonV2 headerV2() {
        return new PersonV2(new Name("Ian", "Wodder"));
    }
}
