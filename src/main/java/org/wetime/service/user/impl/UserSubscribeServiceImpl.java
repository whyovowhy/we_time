package org.wetime.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wetime.entity.user.UserSubscribe;
import org.wetime.mapper.user.UserSubscribeMapper;
import org.wetime.service.user.UserSubscribeService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @Author: Xhy
 * @CreateTime: 2023-11-01 15:05
 */
@Service
public class UserSubscribeServiceImpl  extends ServiceImpl<UserSubscribeMapper, UserSubscribe> implements UserSubscribeService {
}
