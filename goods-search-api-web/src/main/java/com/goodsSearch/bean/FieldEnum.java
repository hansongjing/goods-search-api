package com.goodsSearch.bean;

/**
 * Created by hanhansongjiang on 17/5/9.
 */
public enum FieldEnum {

    TITLE("title"),
    PRICE("price"),
    ITEM_NAME("item_name"),
    CATS("cats"),
    CATSCHILAD("cats.childs.name"),
    CATSCHILDCHILD("cats.childs.childs.name"),

    BRAND("brand");

    private final String name;

    FieldEnum(String name){
        this.name=name;

    }

    public String getName(){
        return name;
    }
}
