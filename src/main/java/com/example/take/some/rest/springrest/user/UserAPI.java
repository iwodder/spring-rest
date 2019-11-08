package com.example.take.some.rest.springrest.user;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
public class UserAPI {

    @Autowired
    UserDataService dataService;

    @Autowired
    ResourceBundleMessageSource messageSource;

    @GetMapping(path="/users/{id}")
    public User getUserById(@PathVariable int id) {
        User u = dataService.getUser(id);
        if (u == null) {
            throw new UserNotFoundException("id-" + id);
        }

        return u;
    }

    @GetMapping(path = "/users")
    public List<User> getUsers() {
        return dataService.getUsers();
    }

    @PostMapping(path="/users")
    public ResponseEntity saveUser(@Valid @RequestBody User user) {
        User u = dataService.save(user);
        URI c = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(u.getId()).toUri();
        return ResponseEntity.created(c).build();
    }

    @DeleteMapping(path="/users/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        dataService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/hello")
    public String helloi18n(@RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("good.morning.message", null, locale);
    }
}
