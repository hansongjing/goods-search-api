package com.goodsSearch;


import com.goodsSearch.bean.AccountInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public Map<String, Object> getHello() {

        HashMap<String,Object> map=new HashMap<String,Object>();


        map.put("account",new AccountInfo("334","eer","hello"));

        return map;

    }
}