package com.example.take.some.rest.springrest.user;

import net.bytebuddy.asm.Advice;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
public class UserAPI {

    @Autowired
    UserDataService dataService;

    @Autowired
    ResourceBundleMessageSource messageSource;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping(path="/users/{id}")
    public User getUserById(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new UserNotFoundException("id-" + id));
    }

    @GetMapping(path = "/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping(path="/users")
    public ResponseEntity saveUser(@Valid @RequestBody User user) {
        User u = userRepository.save(user);
        URI c = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(u.getId()).toUri();
        return ResponseEntity.created(c).build();
    }

    @DeleteMapping(path="/users/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/hello")
    public String helloi18n(@RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("good.morning.message", null, locale);
    }

    @GetMapping("users/{id}/posts")
    public List<Post> getUserPosts(@PathVariable int id) {
        Optional<User> u = userRepository.findById(id);
        return u.orElseThrow(() -> new UserNotFoundException("id-" + id)).getPosts();
    }

    @PostMapping("users/{id}/post")
    public ResponseEntity savePost(
            @PathVariable int id,
            @Valid @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);
        User u = user.orElseThrow(()->new UserNotFoundException("id-" + id));
        post.setUser(u);
        Post p = postRepository.save(post);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}/post/{postId}")
                .buildAndExpand(id, p.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
