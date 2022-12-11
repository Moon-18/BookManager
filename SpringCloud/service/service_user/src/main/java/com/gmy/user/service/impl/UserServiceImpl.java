package com.gmy.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gmy.service.exception.GmyException;
import com.gmy.user.entity.User;
import com.gmy.user.mapper.UserMapper;
import com.gmy.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gmy.utils.JwtUtils;
import com.gmy.utils.MD5;
import com.gmy.utils.R;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 图书馆的管理员列表 服务实现类
 * </p>
 *
 * @author gmy
 * @since 2022-06-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public R login(User user) {
        QueryWrapper<User> query = new QueryWrapper<User>();
        query.eq("ACCOUNT", user.getAccount());
        query.eq("PASSWORD", user.getPassword());
        User one = baseMapper.selectOne(query);
        return one == null ? R.error() : R.ok().data("user", one);
    }

    public R register(User user) {
        user.setPassword(MD5.encrypt(user.getPassword()));//存储加密的密码
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("account", user.getAccount());
        Integer count = baseMapper.selectCount(wrapper);//条件查询 账户

        if (count > 0) {
            return R.error("账号重复");
        } else {
            baseMapper.insert(user);
            return R.ok("注册成功");
        }

    }

    @Override
    public R existUserByAccount(String account) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("account", account);
        User user = baseMapper.selectOne(wrapper);
        return user == null ? R.error() : R.ok().data("user", user);//没有这个用户 是null 条件不成立,返回false
    }
}
