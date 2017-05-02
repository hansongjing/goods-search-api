/**
 * Company
 * Copyright (C) 2014-2017 All Rights Reserved.
 */
package com.goodsSearch.mapper;


import com.goodsSearch.bean.AccountInfo;
import com.goodsSearch.bean.GoodsInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component("goodsInfo")
public interface GoodsInfoRepository extends ElasticsearchRepository<GoodsInfo,String> {
}
