package com.gmy.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gmy.book.entity.Book;
import com.gmy.book.mapper.BookMapper;
import com.gmy.book.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gmy.utils.R;
import com.gmy.utils.NameValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author gmy
 * @since 2022-06-26
 */


@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
    @Override
    public R getCategory() {
        List<Book> lists = baseMapper.selectList(null);//获得全部图书
        Map<String, Integer> mp = new HashMap<>();//名称,数量
        for (Book book : lists) {//遍历全部图书
            mp.merge(book.getKind(), 1, Integer::sum);//它将新值置于给定键下（如果不存在）或更新具有给定值的现有键（UPSERT）
        }
        List<NameValue> l = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : mp.entrySet()) {
            String mapKey = entry.getKey();
            Integer mapValue = entry.getValue();
            l.add(new NameValue(mapKey, mapValue));
        }
        return R.ok().data("category_num", l);
    }

    @Override
    public R updateBookNumByBookName(String bookName, Integer num) {
        QueryWrapper<Book> wrapper = new QueryWrapper<Book>();
        wrapper.eq("name", bookName);
        Book book = baseMapper.selectOne(wrapper);
        if (book == null) {
            return R.error().message("图书不存在");
        }
        if (book.getCurNum() + num >= 0) {
            book.setCurNum(book.getCurNum() + num);
            baseMapper.updateById(book);
            return R.ok();
        } else {
            return R.error().message("数量不足");
        }
    }

    @Override
    public R getOne(String keyword) {
        List<Book> list = new ArrayList<>();
        QueryWrapper<Book> wrapper = new QueryWrapper<Book>();
        wrapper.like("name", keyword);
        list.addAll(baseMapper.selectList(wrapper));

        wrapper = new QueryWrapper<Book>();
        wrapper.like("writer", keyword);
        list.addAll(baseMapper.selectList(wrapper));


        wrapper = new QueryWrapper<Book>();
        wrapper.like("word", keyword);
        list.addAll(baseMapper.selectList(wrapper));

        return list.isEmpty() ? R.error("没有检索到图书") : R.ok().data("bookList", list);
    }
}
