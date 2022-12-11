package com.gmy.borrow.service;

import com.gmy.borrow.entity.Borrow;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gmy.utils.R;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>
 * 借阅信息管理 服务类
 * </p>
 *
 * @author gmy
 * @since 2022-06-27
 */
public interface BorrowService extends IService<Borrow> {
    public R finishBorrow(String userAccount, String bookName, boolean state, String message);

    public R addBorrow(String userAccount, String bookName, Integer num);

    public R getOne(String userAccount);
}
