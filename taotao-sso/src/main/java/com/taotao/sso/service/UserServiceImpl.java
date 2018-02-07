package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.dao.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.sso.dao.JedisClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午2:42 on 17-12-15.
 * @Modified By:
 */

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private TbUserMapper userMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;
    @Value("${SSO_SESSION_EXPIRE}")
    private Integer SSO_SESSION_EXPIRE;

    public TaotaoResult checkData(String content,Integer type){
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        if(type == 1){
            criteria.andUsernameEqualTo(content);
        }else if(type == 2){
            criteria.andPhoneEqualTo(content);
        }else{
            criteria.andEmailEqualTo(content);
        }

        List<TbUser> list = userMapper.selectByExample(example);
        if(list.size() == 0 || list == null){
            return TaotaoResult.ok(true);
        }else
            return TaotaoResult.ok(false);
    }

    @Override
    public TaotaoResult createUser(TbUser user) {
        user.setCreated(new Date());
        user.setUpdated(new Date());
        //md5
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userMapper.insert(user);
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult userLogin(String username, String password,
                                  HttpServletRequest request, HttpServletResponse response) {

        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> list = userMapper.selectByExample(example);
        //如果没有此用户名
        if (null == list || list.size() == 0) {
            return TaotaoResult.build(400, "用户名或密码错误");
        }

        TbUser user = list.get(0);
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        if(!password.equals(user.getPassword())){
            return TaotaoResult.build(400,"用户名或者密码不对");
        }

        String token = UUID.randomUUID().toString();
        //保存用户之前，把用户对象中的密码清空。
        user.setPassword(null);
        //把用户信息写入redis
        jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
        //设置session的过期时间
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);

        //添加cookie逻辑
        CookieUtils.setCookie(request,response,"TT_TOKEN",token);

        //返回token
        return TaotaoResult.ok(token);
    }


    @Override
    public TaotaoResult getUserByToken(String token) {

        String json = jedisClient.get(REDIS_USER_SESSION_KEY+":"+token);
        if (StringUtils.isBlank(json)) {
            return TaotaoResult.build(400, "此session已经过期，请重新登录");
        }

        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
        return TaotaoResult.ok(JsonUtils.jsonToPojo(json,TbUser.class));
    }
}
