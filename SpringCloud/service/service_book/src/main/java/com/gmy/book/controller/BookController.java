package com.gmy.book.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gmy.book.entity.Book;
import com.gmy.book.service.BookService;
import com.gmy.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author gmy
 * @since 2022-06-26
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/getAll")
    public R getBookList() {
        return R.ok().data("items", bookService.list(null));
    }

    //3.分页查询讲师的方法
    @ApiOperation("书籍分页查询")
    @GetMapping("/pageBook/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit) {
        //创建page对象
        Page<Book> pageTeacher = new Page<>(current, limit);
        //调用方法实现分页
        //调用方法的时候，底层封装，把分页所有的数据封装到pageTeacher对象里面
        bookService.page(pageTeacher, null);
        long total = pageTeacher.getTotal();//总记录数
        List<Book> records = pageTeacher.getRecords();//每页记录数
        return R.ok().data("total", total).data("items", records);
    }

    @PostMapping("/addBook")
    public R addBook(@RequestBody Book book) {
        boolean save = bookService.save(book);
        return save ? R.ok() : R.error();
    }

    @PostMapping("/updateBook")
    public R updateBook(@RequestBody Book book) {
        return bookService.updateById(book) ? R.ok() : R.error();
    }

    @GetMapping("/updateBookNumByBookName/{bookName}/{num}")
    @ApiOperation("修改图书数量,修改量是num")
    public R updateBookNum(@PathVariable String bookName, @PathVariable Integer num) {
        return bookService.updateBookNumByBookName(bookName, num);
    }

    @DeleteMapping("/deleteBook/{id}")
    public R deleteBook(@PathVariable String id) {
        return bookService.removeById(id) ? R.ok() : R.error();
    }

    @GetMapping("/getCategory")
    public R getCategory() {
        return bookService.getCategory();
    }

    @GetMapping("/getOne/{keyword}")
    public R getOne(@PathVariable String keyword) {
        return bookService.getOne(keyword);
    }
}

