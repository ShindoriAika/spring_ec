package com.example.spring_ec.Model;

import java.util.ArrayList;
import java.util.List;

public class CartBean {
    private List<ItemBean> itemList;
    private int totalPrice;

    public CartBean(){
        this.itemList = new ArrayList<>();
    }

    public List<ItemBean> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemBean> itemList) {
        this.itemList = itemList;
        int totalPrice = 0;
        for(ItemBean itemBean:itemList){
            totalPrice += (itemBean.getItem().getPrice() * itemBean.getPurchaseNum());
        }
        this.totalPrice = totalPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
