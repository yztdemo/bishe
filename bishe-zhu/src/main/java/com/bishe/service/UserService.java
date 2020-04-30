package com.bishe.service;


import com.aliyuncs.exceptions.ClientException;
import com.bishe.entity.User;
import com.bishe.mapper.UserMapper;
import com.bishe.sms.NumberUtils;
import com.bishe.sms.SmsProperties;
import com.bishe.sms.SmsUtils;
import com.bishe.untils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserService {

    private String code;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SmsProperties smsProperties;
    @Autowired
    private SmsUtils smsUtils;


    @Autowired
    private UserMapper userMapper;


    public User selectuserbyname(User user) {
        User user1 = this.userMapper.selectOne(user);
        if (user1==null){
            return null;
        }else{
            return user1;
        }
    }

    public void sendphoneservice(String phonenumber,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (StringUtils.isBlank(phonenumber)){
            return;
        }else {
           this.code = NumberUtils.generateCode(6);
            redisUtil.set("code",code,300);
            try {
                this.smsUtils.sendSms(phonenumber,code,this.smsProperties.getSignName(),this.smsProperties.getVerifyCodeTemplate());
            } catch (ClientException e) {
                e.printStackTrace();
            }
        }
    }
}
