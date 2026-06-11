package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 新增/修改用户
    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        User res = userService.saveOrUpdateUser(user);
        userService.printAllCaffeineCache(); // 操作后打印缓存
        return res;
    }

    // 查询用户
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        User res = userService.getUserById(id);
        userService.printAllCaffeineCache(); // 操作后打印缓存
        return res;
    }

    // 删除用户
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        userService.printAllCaffeineCache(); // 操作后打印缓存
        return "删除成功";
    }
}
