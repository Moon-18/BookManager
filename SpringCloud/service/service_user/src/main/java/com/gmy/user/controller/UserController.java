package com.gmy.user.controller;


import com.gmy.user.entity.User;
import com.gmy.user.service.UserService;
import com.gmy.utils.JwtUtils;
import com.gmy.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 图书馆的管理员列表 前端控制器
 * </p>
 *
 * @author gmy
 * @since 2022-06-27
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    //登录
    @PostMapping("login")
    public R loginUser(@RequestBody User user) {
        return userService.login(user);
    }

    //注册
    @PostMapping("register")
    public R registerUser(@RequestBody User user) {
        return userService.register(user);
    }

    @GetMapping("/getAll")
    public R getUserList() {
        return R.ok().data("items", userService.list(null));
    }

    @GetMapping("/getUser/{id}")
    public R getUser(@PathVariable String id) {
        return R.ok().data("items", userService.getById(id));
    }

    @PostMapping("/updateUser")
    public R updateUser(@RequestBody User user) {
        return userService.updateById(user) ? R.ok() : R.error();
    }

    @DeleteMapping("/deleteUser/{id}")
    public R deleteUser(@PathVariable String id) {
        return userService.removeById(id) ? R.ok() : R.error();
    }

    @GetMapping("/getUserByAccount/{account}")
    public R getUserByAccount(@PathVariable String account) {
        return userService.existUserByAccount(account);
    }
}

