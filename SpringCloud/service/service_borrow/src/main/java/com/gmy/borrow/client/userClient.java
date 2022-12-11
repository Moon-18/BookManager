package com.gmy.borrow.client;


import com.gmy.borrow.entity.User;
import com.gmy.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "service-user", fallback = userFeignClient.class)
//@FeignClient(name = "service-user")
public interface userClient {
    @GetMapping("/user/getUser/{id}")
    public R getUser(@PathVariable("id") String id);

    @GetMapping("/user/getUserByAccount/{account}")
    public R getUserByAccount(@PathVariable("account") String account);

    @PostMapping("/user/updateUser")
    public R updateUser(@RequestBody User user);

}
