package com.example.spring_ec.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring_ec.Entity.UserEntity;
import com.example.spring_ec.Model.CartBean;
import com.example.spring_ec.Model.ItemBean;
import com.example.spring_ec.Repository.ItemRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class PurchaseController {
    @Autowired
    HttpSession session;

    @Autowired
    ItemRepository itemRepository;

    @RequestMapping("/showConfirm")
    public ModelAndView showConfirm(ModelAndView mv){
        CartBean cart = (CartBean)session.getAttribute("cart");
        mv.addObject("cart", cart);
        UserEntity loginUser = (UserEntity)session.getAttribute("loginUser");
        mv.addObject("userName", loginUser.getName());
        mv.setViewName("confirm");
        return mv;
    }

    @RequestMapping("/purchase")
    public ModelAndView purchase(ModelAndView mv){
        CartBean cart = (CartBean)session.getAttribute("cart");
        UserEntity loginUser = (UserEntity)session.getAttribute("loginUser");
        mv.addObject("userName", loginUser.getName());
        List<ItemBean> itemList = cart.getItemList();
        for (ItemBean cartItem : itemList) {
            if(cartItem.getItem().getStock()>=cartItem.getPurchaseNum()){
                cartItem.getItem().setStock(cartItem.getItem().getStock()-cartItem.getPurchaseNum());
                itemRepository.save(cartItem.getItem());
            }else{
                mv.addObject("cart", cart);
                mv.addObject("msg", "購入数が在庫数を上回っています。購入数を変更してください");
                mv.setViewName("confirm");
                return mv;
            }
        }
        mv.setViewName("completed");
        return mv;
    }
}
