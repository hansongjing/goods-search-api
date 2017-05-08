package com.goodsSearch.bean;

import lombok.Data;

/**
 * Created by hanhansongjiang on 17/5/7.
 */
@Data
public class SearchForm {

    //1.查询条件

    /**
     * 搜索关键字
     */
    private  String queryString;



    //2。过滤条件相关


    //品牌
    private String  brand;

    //属性
    private String prop;

    // 目录
    private String  cats;



    //3.分页相关

    /**
     *第几页
     */
    private  Integer page=0;
    /**
     *多少页
     */
    private  Integer pageSize=0;


    //4 排序字段


    private  String order;//排序规则,使用,号分割


    //5是否包括聚合

    private  boolean isAggs;



}
