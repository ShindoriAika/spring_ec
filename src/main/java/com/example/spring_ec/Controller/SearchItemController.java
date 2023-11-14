package com.example.spring_ec.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring_ec.Entity.ItemEntity;
import com.example.spring_ec.Model.SeachItemLogic;
import com.example.spring_ec.Repository.CategoryRepository;
import com.example.spring_ec.Repository.ItemRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class SearchItemController {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SeachItemLogic searchItemLogic;

    @Autowired
    HttpSession session;

    @RequestMapping("/showSearch")
    public ModelAndView showSearch(ModelAndView mv){
        mv = searchItemLogic.showSearch(true, mv);
        return mv;
    }

    @RequestMapping("/search")
    public ModelAndView search(
            @RequestParam("category") int categoryCode,
            @RequestParam("keyword") String keyword,
            ModelAndView mv
    ){
        List<ItemEntity> itemList = null;
        if(categoryCode==0&&(keyword.equals("")||keyword==null)){
            itemList = itemRepository.findAll();
            mv.addObject("items", itemList);
        }else if(keyword.equals("")||keyword==null){
            itemList = itemRepository.findByCategoryCode(categoryCode);
        }else if(categoryCode==0){
            itemList = itemRepository.findByNameLike("%"+keyword+"%");
        }else{
            itemList = itemRepository.findByCategoryCodeAndNameLike(categoryCode, "%"+keyword+"%");
        }
        if(itemList.size()!=0){
            mv.addObject("items", itemList);
        }else{
            mv.addObject("msg", "該当する商品がありません");
        }
        mv = searchItemLogic.showSearch(false, mv);
        return mv;
    }
}
