package com.example.spring_ec.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring_ec.Entity.ItemEntity;
import com.example.spring_ec.Entity.UserEntity;
import com.example.spring_ec.Model.CartBean;
import com.example.spring_ec.Model.CartLogic;
import com.example.spring_ec.Model.ItemBean;
import com.example.spring_ec.Model.SeachItemLogic;
import com.example.spring_ec.Repository.ItemRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    SeachItemLogic searchItemLogic;

    @Autowired
    CartLogic cartLogic;

    @Autowired
    HttpSession session;


    @RequestMapping("/addItem")
    public ModelAndView addItem(
            @RequestParam("code") int code,
            @RequestParam("purchaseNum") int purchaseNum,
            ModelAndView mv
    ){
        CartBean cart = (CartBean)session.getAttribute("cart");
        ItemEntity item = itemRepository.findByCode(code);
        if(cart==null){
            cart = new CartBean();
            cart.getItemList().add(new ItemBean(item, purchaseNum));
        }else{
            List<ItemBean> itemList = cart.getItemList();
            boolean addFlg = true;
            for(ItemBean cartItem : itemList){
                if(cartItem.getItem().getCode()==code){
                    if((cartItem.getPurchaseNum()+purchaseNum)>cartItem.getItem().getStock()){
                        mv.addObject("msg", "購入数が在庫数を上回っています");
                        mv.addObject("item", item);
                        UserEntity loginUser = (UserEntity)session.getAttribute("loginUser");
                        mv.addObject("userName", loginUser.getName());
                        mv.setViewName("item");
                        return mv;
                    }
                    cartItem.setPurchaseNum(cartItem.getPurchaseNum()+purchaseNum);
                    addFlg = false;
                    break;
                }
            }
            if(addFlg){
                itemList.add(new ItemBean(item, purchaseNum));
            }
        }
        cart.setItemList(cart.getItemList());
        session.setAttribute("cart", cart);
        searchItemLogic.showSearch(true, mv);
        return mv;
    }

    @RequestMapping("/showCart")
    public ModelAndView showCart(ModelAndView mv){
        CartBean cart = (CartBean)session.getAttribute("cart");
        mv = cartLogic.showCart(cart, mv);
        return mv;
    }

    @RequestMapping("/updatePurchaseNum")
    public ModelAndView updatePurchaseNum(
            @RequestParam("code") int code,
            @RequestParam("purchaseNum") int purchaseNum,
            ModelAndView mv
    ){
        CartBean cart = (CartBean)session.getAttribute("cart");
        List<ItemBean> itemList = cart.getItemList();
        for(ItemBean cartItem : itemList){
            if(cartItem.getItem().getCode()==code){
                cartItem.setPurchaseNum(purchaseNum);
            }
        }
        cart.setItemList(itemList);
        session.setAttribute("cart", cart);
        mv = cartLogic.showCart(cart, mv);
        return mv;
    }

    @RequestMapping("/deleteItem")
    public ModelAndView deleteItem(
            @RequestParam("code") int code,
            ModelAndView mv
    ){
        CartBean cart = (CartBean)session.getAttribute("cart");
        int deleteItemIndex = 0;
        List<ItemBean> itemList = cart.getItemList();
        for(ItemBean cartItem : itemList){
            if(cartItem.getItem().getCode()==code){
                cart.getItemList().indexOf(cartItem);
                break;
            }
        }
        itemList.remove(deleteItemIndex);
        cart.setItemList(itemList);
        if(itemList.size()==0){
            cart=null;
        }
        session.setAttribute("cart", cart);
        mv = cartLogic.showCart(cart, mv);
        return mv;
    }
}
