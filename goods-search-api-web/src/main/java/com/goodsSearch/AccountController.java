package com.goodsSearch;

import com.goodsSearch.bean.AccountInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanhansongjiang on 17/5/21.
 */
@Controller
public class AccountController {

    @ResponseBody
    @RequestMapping(value = "/getHello", method = RequestMethod.GET)
    public Map<String, Object> getHello() {

        HashMap<String,Object> map=new HashMap<String,Object>();


        map.put("account",new AccountInfo("334","eer","hello"));

        return map;

    }
}
