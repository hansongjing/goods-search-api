package com.goodsSearch.bean.cat;

import lombok.Data;

import java.util.List;

/**
 * Created by hanhansongjiang on 17/4/28.
 */
@Data
public class Cat {


    private String id;

    private String name;


    private List<Cat> childs;


}
