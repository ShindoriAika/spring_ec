package com.example.spring_ec.Model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring_ec.Entity.UserEntity;

import jakarta.servlet.http.HttpSession;

@Service
public class CartLogic {
    @Autowired
    HttpSession session;

    public ModelAndView showCart(CartBean cart, ModelAndView mv){
        mv.addObject("cart", cart);
        UserEntity loginUser = (UserEntity)session.getAttribute("loginUser");
        mv.addObject("userName", loginUser.getName());
        mv.setViewName("cart");
        return mv;
    }
}
