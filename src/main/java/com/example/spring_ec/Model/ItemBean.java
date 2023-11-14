package com.example.spring_ec.Model;

import com.example.spring_ec.Entity.ItemEntity;

public class ItemBean {
    private ItemEntity item;
    private int purchaseNum;

    public ItemBean(ItemEntity item, int purchaseNum){
        this.item = item;
        this.purchaseNum = purchaseNum;
    }

    public ItemEntity getItem() {
        return item;
    }

    public int getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(int purchaseNum) {
        this.purchaseNum = purchaseNum;
    }
}
