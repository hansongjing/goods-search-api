package com.goodsSearch;

import com.goodsSearch.bean.GoodsInfo;
import com.goodsSearch.mapper.GoodsInfoRepository;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.search.MultiMatchQuery;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * Created by hanhansongjiang on 17/4/28.
 */


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SearchTest
{

    @Autowired
    private GoodsInfoRepository goodsInfoRepository;


    @Autowired
    private ElasticsearchTemplate esTemplate;


    @Test
    public void test(){
      GoodsInfo goodsInfo= goodsInfoRepository.findOne("g59338");
        goodsInfo.getAdminId();
    }

    @Test
    public void testBuilder(){
        //2015-08-20 12:27:11
        QueryBuilder qb = QueryBuilders.rangeQuery("costPrice").from(0).to(80);
        SearchResponse sResponse = esTemplate.getClient().prepareSearch("shop")
                .setTypes("Product")
                // 设置search type
                // 常用search type用：query_then_fetch
                // query_then_fetch是先查到相关结构，然后聚合不同node上的结果后排序
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                // 查询的termName和termvalue
                .setQuery(qb)
                // 设置排序field
                .addSort("costPrice", SortOrder.DESC)
                // 设置分页
                .setFrom(0).setSize(60).execute().actionGet();
        int tShards = sResponse.getTotalShards();
        long timeCost = sResponse.getTookInMillis();
        int sShards = sResponse.getSuccessfulShards();
        System.out.println(tShards + "," + timeCost + "," + sShards);
        SearchHits hits = sResponse.getHits();
        long count = hits.getTotalHits();
        SearchHit[] hitArray = hits.getHits();
        for (int i = 0; i < count-1; i++) {
            SearchHit hit = hitArray[i];
            Map<String, Object> fields = hit.getSource();
            for (String key : fields.keySet()) {
                System.out.println(key);
                System.out.println(fields.get(key));
            }
        }

    }

    @Test
    public void testTermBuilder(){
        QueryBuilder qb=QueryBuilders.rangeQuery("costPrice").from(0).to(80);
//        QueryBuilder queryBuilder=QueryBuilders.matchPhraseQuery("title","安踏女鞋");

        SearchResponse sResponse =  esTemplate.getClient().prepareSearch("shop")
                .setTypes("Product")
                // 设置search type
                // 常用search type用：query_then_fetch
                // query_then_fetch是先查到相关结构，然后聚合不同node上的结果后排序
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                // 查询的termName和termvalue
                .setQuery(qb)
//                setQuery(qb)
                // 设置排序field
                .addSort("costPrice", SortOrder.DESC)
                // 设置分页
                .setFrom(0).setSize(60).execute().actionGet();

        SearchHits hits = sResponse.getHits();
        System.out.print(hits);



    }



}
