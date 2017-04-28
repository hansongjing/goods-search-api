package com.goodsSearch.bean;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * Created by hanhansongjiang on 17/4/28.
 */

@Document(indexName = "goods",type = "products", shards = 1,replicas = 0, refreshInterval = "-1")
public class GoodsInfo {

    private String  adminId;


    private String domainType;


    private long  endTime;



}
