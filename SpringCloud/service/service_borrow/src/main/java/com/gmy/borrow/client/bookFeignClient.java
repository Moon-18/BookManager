package com.gmy.borrow.client;

import com.gmy.utils.R;
import org.springframework.stereotype.Component;

//下面的注解不加报错
@Component
public class bookFeignClient implements bookClient {

    @Override
    public R updateBookNum(String bookName, Integer num) {
        return R.error().message("熔断器");
    }
}
