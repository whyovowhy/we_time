package org.wetime.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.wetime.constant.RedisConstant;
import org.wetime.entity.Captcha;
import org.wetime.entity.user.User;
import org.wetime.entity.vo.FindPWVO;
import org.wetime.entity.vo.RegisterVO;
import org.wetime.exception.BaseException;
import org.wetime.service.CaptchaService;
import org.wetime.service.LoginService;
import org.wetime.service.user.UserService;
import org.wetime.util.RedisCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @description:
 * @Author: Xhy
 * @CreateTime: 2023-11-04 12:38
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    @Override
    public User login(User user) {
        final String password = user.getPassword();
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        user = userService.getOne(wrapper.eq(User::getEmail, user.getEmail()));
        if (ObjectUtils.isEmpty(user)){
            throw new BaseException("没有该账号");
        }

        if (!password.equals(user.getPassword())) {
            throw new BaseException("密码不一致");
        }

        return user;
    }

    @Override
    public Boolean checkCode(String email, Integer code) {
        if (ObjectUtils.isEmpty(email) || ObjectUtils.isEmpty(code)){
            throw new BaseException("参数为空");
        }
        final Object o = redisCacheUtil.get(RedisConstant.EMAIL_CODE + email);

        if (!code.toString().equals(o)){
            throw new BaseException("验证码不正确");

        }
        return true;
    }

    @Override
    public void captcha(String uuId, HttpServletResponse response) throws IOException {
        if (ObjectUtils.isEmpty(uuId)) throw new IllegalArgumentException("uuid不能为空");
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        BufferedImage image = captchaService.getCaptcha(uuId);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    @Override
    public Boolean getCode(Captcha captcha) throws Exception {

        return captchaService.validate(captcha);
    }

    @Override
    public Boolean register(RegisterVO registerVO) throws Exception {
        // 注册成功后删除图形验证码
        if (userService.register(registerVO)){
            captchaService.removeById(registerVO.getUuid());
            return true;
        }
        return false;
    }

    @Override
    public Boolean findPassword(FindPWVO findPWVO) {
        final Boolean b = userService.findPassword(findPWVO);
        return b;
    }
}
