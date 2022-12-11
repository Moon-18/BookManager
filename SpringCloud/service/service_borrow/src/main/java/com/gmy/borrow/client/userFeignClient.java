package com.gmy.borrow.client;

import com.gmy.borrow.entity.User;
import com.gmy.utils.R;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class userFeignClient implements userClient {
    @Override
    public R getUser(String id) {
        return R.error().message("熔断器");
    }

    @Override
    public R getUserByAccount(String account) {
        //hystrix容错的方法,失败的时候调用
        return R.error().message("熔断器");
    }

    @Override
    public R updateUser(User user) {
        return R.error().message("熔断器");
    }
}
