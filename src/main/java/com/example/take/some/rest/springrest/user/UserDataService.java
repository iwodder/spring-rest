package com.example.take.some.rest.springrest.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDataService {
    private static List<User> users = new ArrayList<>();
    private static int userCtr = 5;

    static {
        users.add(new User(1, "Ian", LocalDate.of(1990, 6, 9)));
        users.add(new User(2, "Chey", LocalDate.of(1991, 7, 9)));
        users.add(new User(3, "Justin", LocalDate.of(1992, 8, 9)));
        users.add(new User(4, "Ram", LocalDate.of(1993, 9, 9)));
        users.add(new User(5, "Will", LocalDate.of(1994, 10, 9)));
    }

    public User getUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User save(User user) {
        if (user.getId() == 0) {
            user.setId(++userCtr);
        }
        users.add(user);
        return user;
    }

    public List<User> getUsers() {
        return users;
    }

    public int deleteUser(int id) {
        User u = getUser(id);
        if (u == null) {
            throw new UserNotFoundException("id-"+id);
        }
        users.remove(u);
        return id;
    }
}
