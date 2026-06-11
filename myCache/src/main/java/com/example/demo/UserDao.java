package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    // use hashmap to mock db
    private final Map<Long, User> mockDB = new HashMap<>();

    public User getById(Long id) {
        return mockDB.get(id);
    }

    public User update(User user) {
        mockDB.put(user.getId(), user);
        return user;
    }

    public void deleteById(Long id) {
        mockDB.remove(id);
    }
}
