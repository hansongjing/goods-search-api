/**
 * Company
 * Copyright (C) 2014-2017 All Rights Reserved.
 */
package com.goodsSearch.mapper;


import com.goodsSearch.bean.AccountInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author cwenao
 * @version $Id ElasticAccountInfoRepository.java, v 0.1 2017-02-06 10:26 cwenao Exp $$
 */
@Component("elasticAccountInfoRepository")
public interface ElasticAccountInfoRepository extends ElasticsearchRepository<AccountInfo,String> {
    AccountInfo findByAccountName(String accountName);
}
