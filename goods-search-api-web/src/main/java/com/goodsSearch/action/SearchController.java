package com.goodsSearch.action;

import com.goodsSearch.bean.AccountInfo;
import com.goodsSearch.mapper.ElasticAccountInfoRepository;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanhansongjiang on 17/4/27.
 * 功能: 商品搜索
 */
@Controller
public class SearchController {
    @Autowired
    private Client client;

    @Autowired
    private ElasticAccountInfoRepository elasticAccountInfoRepository;

    @Autowired
    private  ElasticsearchTemplate  elasticsearchTemplate;

    @ResponseBody
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public Map<String,Object>   search(){


        Iterable<AccountInfo> iterablet= elasticAccountInfoRepository.findAll();

        List<AccountInfo> list=new ArrayList<AccountInfo>();
        for(AccountInfo accountInfo:iterablet){
          list.add(accountInfo);
        }

        HashMap<String,Object> result=new HashMap<String, Object>();

        elasticsearchTemplate.getSetting("bank");

        result.put("ans",result);

        return result;




    }


    @ResponseBody
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public Map<String,Object>   add(){

        boolean ans=elasticsearchTemplate.createIndex(AccountInfo.class);

         elasticAccountInfoRepository.save(new AccountInfo("001","01","hsj"));

        HashMap<String,Object> result=new HashMap<String, Object>();
        result.put("result","sucess");
        return result;




    }




}
