package com.example.spring_ec.Controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring_ec.Entity.CategoryEntity;
import com.example.spring_ec.Entity.ItemEntity;
import com.example.spring_ec.Entity.UserEntity;
import com.example.spring_ec.Repository.CategoryRepository;
import com.example.spring_ec.Repository.ItemRepository;
import com.example.spring_ec.Repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    HttpSession session;

    @RequestMapping("/")
    public ModelAndView index(ModelAndView mv){
        session.invalidate();
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public ModelAndView login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            ModelAndView mv
    ){
        if(email.equals("")||email==null||password.equals("")||password==null){
            mv.addObject("msg", "メールアドレスとパスワードを入力してください");
            mv.setViewName("login");
        }else{
            UserEntity user = userRepository.findByEmailAndPassword(email, password);
            if(Objects.nonNull(user)){
                session.setAttribute("loginUser", user);
                List<ItemEntity> itemList = itemRepository.findAll();
                List<CategoryEntity> categoryList = categoryRepository.findAll();
                mv.addObject("items", itemList);
                mv.addObject("categories", categoryList);
                mv.addObject("userName", user.getName());
                mv.setViewName("searchItem");
            }else{
                mv.addObject("msg", "メールアドレスかパスワードが正しくありません");
                mv.setViewName("login");
            }
        }
        return mv;
    }
}
