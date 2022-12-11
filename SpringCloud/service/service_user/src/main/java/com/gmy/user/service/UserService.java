package com.gmy.user.service;

import com.gmy.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gmy.utils.R;

/**
 * <p>
 * 图书馆的管理员列表 服务类
 * </p>
 *
 * @author gmy
 * @since 2022-06-27
 */
public interface UserService extends IService<User> {
    //登录方法
    R login(User user);

    R register(User user);

    public R existUserByAccount(String account);
}
