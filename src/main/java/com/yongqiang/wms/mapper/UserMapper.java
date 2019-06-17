package com.yongqiang.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yongqiang.wms.model.user.User;
import org.springframework.stereotype.Component;

/**
 * Created by yantao.chen on 2019-05-20
 */
@Component
public interface UserMapper extends BaseMapper<User> {
    /**
     * 账套id和登录账号查用户信息
     * @param user
     * @return
     */
    User selectUserByLogin(User user);
}
