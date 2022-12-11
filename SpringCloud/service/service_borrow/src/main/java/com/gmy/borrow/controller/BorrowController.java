package com.gmy.borrow.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmy.borrow.client.bookClient;
import com.gmy.borrow.client.userClient;
import com.gmy.borrow.entity.Borrow;
import com.gmy.borrow.entity.User;
import com.gmy.borrow.service.BorrowService;
import com.gmy.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 借阅信息管理 前端控制器
 * </p>
 *
 * @author gmy
 * @since 2022-06-27
 */
@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;

    @GetMapping("/getAll")
    public R getBorrowList() {
        return R.ok().data("items", borrowService.list(null));
    }

    @ApiOperation("借阅信息分页查询")
    @GetMapping("/pageBorrow/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit) {
        //创建page对象
        Page<Borrow> pageTeacher = new Page<>(current, limit);
        //调用方法实现分页
        //调用方法的时候，底层封装，把分页所有的数据封装到pageTeacher对象里面
        borrowService.page(pageTeacher, null);
        long total = pageTeacher.getTotal();//总记录数
        List<Borrow> records = pageTeacher.getRecords();//每页记录数
        return R.ok().data("total", total).data("items", records);
    }

    @GetMapping("/addBorrow/{userAccount}/{bookName}/{num}")
    @ApiOperation("增加一条借阅信息")
    public R addBorrow(@PathVariable("userAccount") String userAccount, @PathVariable String bookName, @PathVariable Integer num) {
        return borrowService.addBorrow(userAccount, bookName, num);
    }

    @GetMapping("/finishBorrow/{userAccount}/{bookName}/{state}/{message}")
    @ApiOperation("修改借阅信息,如正常,延期,损坏等 使用 , 进行分割,类型是一个用逗号分割的字符串")
    public R finishBorrow(@PathVariable String userAccount, @PathVariable String bookName, @PathVariable boolean state, @PathVariable String message) {
        return borrowService.finishBorrow(userAccount, bookName, state, message);
    }

    @GetMapping("/getOne/{userAccount}")
    public R getOne(@PathVariable String userAccount) {
        return borrowService.getOne(userAccount);
    }

}
