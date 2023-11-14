package com.example.spring_ec.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring_ec.Entity.ItemEntity;
import com.example.spring_ec.Entity.UserEntity;
import com.example.spring_ec.Repository.ItemRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ItemController {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    HttpSession session;

    @RequestMapping("/item")
    public ModelAndView showItem(
            @RequestParam("code") int code,
            ModelAndView mv
    ){
        ItemEntity item = itemRepository.findByCode(code);
        mv.addObject("item", item);
        UserEntity loginUser = (UserEntity)session.getAttribute("loginUser");
        mv.addObject("userName", loginUser.getName());
        mv.setViewName("item");
        return mv;
    }
}
