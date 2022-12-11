package com.gmy.borrow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmy.borrow.entity.Borrow;
import com.gmy.borrow.entity.User;
import com.gmy.borrow.mapper.BorrowMapper;
import com.gmy.borrow.service.BorrowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gmy.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 借阅信息管理 服务实现类
 * </p>
 *
 * @author gmy
 * @since 2022-06-27
 */
@Service
public class BorrowServiceImpl extends ServiceImpl<BorrowMapper, Borrow> implements BorrowService {

    @Autowired
    private com.gmy.borrow.client.bookClient bookClient;

    @Autowired
    private com.gmy.borrow.client.userClient userClient;

    @Override
    public R addBorrow(String userAccount, String bookName, Integer num) {

        R userR = userClient.getUserByAccount(userAccount);
        if (!userR.getSuccess()) {
            return R.error().message("用户不存在");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.convertValue(userR.getData().get("user"), User.class);//string 有bug
//        User user = userR.getData().get("user");
        if (user.getMaxNum() <= 0) {
            return R.error().message("借阅数量已达上限");
        }
        user.setMaxNum(user.getMaxNum() - 1);
        userClient.updateUser(user);//更新用户信息

        R result = bookClient.updateBookNum(bookName, num);
        if (result.getSuccess()) {//减完一本之后如果余量大于0,能借
            Borrow borrow = new Borrow();
            borrow.setBookName(bookName);
            borrow.setReaderAccount(userAccount);
            borrow.setState(false);
            //计算出应该归还时间
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, user.getMaxTime());
            Date newDate = cal.getTime();
            borrow.setDeadline(newDate);
            baseMapper.insert(borrow);
        }
        return result;
    }

    @Override
    public R finishBorrow(String userAccount, String bookName, boolean state, String message) {//暫時不支持自定义数量吧
        //是否在规定时间内
        QueryWrapper<Borrow> wrapper = new QueryWrapper<>();
        wrapper.eq("READER_ACCOUNT", userAccount);//readerAccount
        wrapper.eq("BOOK_NAME", bookName);
        wrapper.eq("STATE", false);

        List<Borrow> currents = baseMapper.selectList(wrapper);//多条借阅信息的处理
//        Borrow currentBorrow = baseMapper.selectOne(wrapper);
        if (currents.size() == 0) {//已经完成借阅,无需重复操作
            return R.error().message("找不到符合条件已借出的记录");
        }
        Borrow currentBorrow = currents.get(0);
        currentBorrow.setState(true);//一条记录还一次就行

        Date now = new Date();
        String insertMessage = "";
        if (state) {//正常归还,不进行备注了
            if (currentBorrow.getDeadline().after(now)) {//此日期是否在指定日期之前,未超期
                insertMessage = ",按时归还";
            } else {
                insertMessage = ",超时归还";
            }
        }
        currentBorrow.setMessage(message + insertMessage);
        //用户信息
        R userR = userClient.getUserByAccount(userAccount);
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.convertValue(userR.getData().get("user"), User.class);//string 有bug
        user.setMaxNum(user.getMaxNum() + 1);
        userClient.updateUser(user);//更新用户信息


        R res = bookClient.updateBookNum(bookName, 1);//增加图书数量
        if (!res.getSuccess()) {//服务失败了,就不修改,直接返回结果
            return res;
        }
        return baseMapper.updateById(currentBorrow) == 1 ? R.ok() : R.error();
    }

    @Override
    public R getOne(String userAccount) {
        QueryWrapper<Borrow> wrapper = new QueryWrapper<>();
        wrapper.eq("READER_ACCOUNT", userAccount);//readerAccount
        return R.ok().data("items", baseMapper.selectList(wrapper));
    }
}


