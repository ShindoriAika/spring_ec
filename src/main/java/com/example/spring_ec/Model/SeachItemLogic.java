package com.example.spring_ec.Model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring_ec.Entity.CategoryEntity;
import com.example.spring_ec.Entity.ItemEntity;
import com.example.spring_ec.Entity.UserEntity;
import com.example.spring_ec.Repository.CategoryRepository;
import com.example.spring_ec.Repository.ItemRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class SeachItemLogic {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    HttpSession session;

    public ModelAndView showSearch(boolean findAllFlg, ModelAndView mv){
        if(findAllFlg){
            List<ItemEntity> itemList = itemRepository.findAll();
            mv.addObject("items", itemList);
        }
        UserEntity loginUser = (UserEntity)session.getAttribute("loginUser");
        mv.addObject("userName", loginUser.getName());
        List<CategoryEntity> categoryList = categoryRepository.findAll();
        mv.addObject("categories", categoryList);
        mv.setViewName("searchItem");
        return mv;
    }
}
