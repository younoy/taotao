package com.taotao.portal.interceptor;

import com.taotao.common.utils.CookieUtils;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.PortalUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午1:41 on 17-12-16.
 * @Modified By:
 */
public class LoginInterceptor  implements HandlerInterceptor{

    @Autowired
    private PortalUserServiceImpl userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse, Object o) throws Exception {

        //在handler之前处理
        String token = CookieUtils.getCookieValue(httpServletRequest,"TT_TOKEN");
        TbUser user = userService.getUser(token);
        if(user == null){
            httpServletResponse.sendRedirect(userService.SSO_BASE_URL + userService.SSO_LOGIN_URL+
                    "?redirect=" + httpServletRequest.getRequestURL());
            return false;
        }
        httpServletRequest.setAttribute("user",user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //在handler之后
    }
}
