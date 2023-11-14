package com.example.spring_ec.Controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring_ec.Entity.UserEntity;
import com.example.spring_ec.Repository.UserRepository;

@Controller
public class AddUserController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/showAddUser")
    public ModelAndView index(ModelAndView mv){
        mv.setViewName("addUser");
        return mv;
    }

    @RequestMapping(value="/addUser", method=RequestMethod.POST)
    public ModelAndView addUser(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            ModelAndView mv
    ){
        if(name.equals("")||name==null||email.equals("")||email==null||password.equals("")||password==null){
            mv.addObject("msg", "未入力の項目があります");
            mv.setViewName("addUser");
        }else{
            UserEntity user = userRepository.findByEmail(email);
            if(Objects.nonNull(user)){
                mv.addObject("msg", "メールアドレスが既に登録されています");
                mv.setViewName("addUser");
            }else{
                user = new UserEntity(name, email, password);
                userRepository.save(user);
                mv.addObject("msg", "新規登録が完了しました");
                mv.setViewName("login");
            }
        }
        return mv;
    }
}
