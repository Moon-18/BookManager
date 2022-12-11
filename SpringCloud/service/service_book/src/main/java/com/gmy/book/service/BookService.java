package com.gmy.book.service;

import com.gmy.book.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gmy.utils.R;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author gmy
 * @since 2022-06-26
 */
public interface BookService extends IService<Book> {
    public R getCategory();

    public R updateBookNumByBookName(String bookName, Integer num);

    public R getOne(String keyword);
}
