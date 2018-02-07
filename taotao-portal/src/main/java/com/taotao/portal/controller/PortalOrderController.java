package com.taotao.portal.controller;

import com.taotao.common.utils.CookieUtils;
import com.taotao.pojo.TbUser;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.PortalCatService;
import com.taotao.portal.service.PortalOrderService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午2:34 on 17-12-18.
 * @Modified By:
 */

@Controller
@RequestMapping("/order")
public class PortalOrderController {

    @Autowired
    private PortalCatService cartService;
    @Autowired
    private PortalOrderService orderService;

    @RequestMapping("/order-cart")
    public String showOrderCart(HttpServletRequest request, HttpServletResponse response, Model model) {
        //取购物车商品列表
        List<CartItem> list = cartService.getCartItemList(request, response);
        //传递给页面
        model.addAttribute("cartList", list);

        return "order-cart";
    }

    @RequestMapping("/create")
    public String createOrder(Order order,Model model,HttpServletRequest request){

        //为什么不从cookie中获取
        TbUser user = (TbUser) request.getAttribute("user");
        order.setUserId(user.getId());
        order.setBuyerNick(user.getUsername());

        String result = orderService.createOrder(order);
        model.addAttribute("orderId", result);
        model.addAttribute("payment", order.getPayment());
        model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
        return "success";
    }
}
