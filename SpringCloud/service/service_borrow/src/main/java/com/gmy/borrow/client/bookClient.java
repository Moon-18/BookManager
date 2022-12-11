package com.gmy.borrow.client;

import com.gmy.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "service-book", fallback = bookFeignClient.class)
//@FeignClient(name = "service-book")
public interface bookClient {
    //pathVaiable注解需要指定参数名称
    //错误的原因是没有加前缀?找不到
    @GetMapping("/book/updateBookNumByBookName/{bookName}/{num}")
    public R updateBookNum(@PathVariable("bookName") String bookName, @PathVariable("num") Integer num);

}
