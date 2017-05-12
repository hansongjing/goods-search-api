package com.goodsSearch.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodsSearch.bean.FieldEnum;
import com.goodsSearch.bean.GoodsInfo;
import com.goodsSearch.bean.SearchForm;
import com.goodsSearch.mapper.ElasticAccountInfoRepository;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hanhansongjiang on 17/4/27.
 * 功能: 商品搜索
 */
@Controller
public class SearchController {


    @Autowired
    private ElasticAccountInfoRepository elasticAccountInfoRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Map<String, Object> search(@RequestBody SearchForm searchForm) {


        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(searchForm.getQueryString(), FieldEnum.TITLE.getName());


        //过滤

        QueryBuilder filterQuery = QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery(searchForm.getBrand(), FieldEnum.BRAND.getName()))
                .must(buildCatsQuery(searchForm.getCats()));

        QueryBuilder booleanQuery = QueryBuilders.boolQuery().filter(filterQuery);


        SearchResponse sResponse = elasticsearchTemplate.getClient().prepareSearch("shop")
                .setTypes("Product")
                // 设置search type
                // 常用search type用：query_then_fetch
                // query_then_fetch是先查到相关结构，然后聚合不同node上的结果后排序
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                // 查询的termName和termvalue
                .setQuery(multiMatchQueryBuilder)
                .setQuery(booleanQuery)
                // 设置排序field
                .addSort(searchForm.getSortField(),SortOrder.DESC)
                .setFrom(0).setSize(10).execute().actionGet();

        SearchHits hits = sResponse.getHits();


        ObjectMapper objectMapper = new ObjectMapper();

        List<GoodsInfo> tradeList = Stream
                .of(hits.getHits())
                .map(searchHitFields -> {
                    GoodsInfo trade = null;
                    try {
                        trade = objectMapper.readValue(searchHitFields.source(), GoodsInfo.class);
                    } catch (IOException e) {

                    }
                    return trade;
                })
                .collect(Collectors.toList());


        tradeList.stream().forEach(t -> {
//            System.out.println(t.getSpuAddTime()+"---"+t.getCostPrice());

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    sdf.format(t.getSpuAddTime());
                    System.out.println(sdf.format(t.getSpuAddTime()) + "---" + t.getCostPrice());

                }

        );

        HashMap<String, Object> result = new HashMap<String, Object>();

        elasticsearchTemplate.getSetting("bank");

        result.put("ans", result);

        return result;

    }

    private QueryBuilder buildCatsQuery(String cats) {

        if (StringUtils.isEmpty(cats)){
            String cat[]=cats.split(",");
            BoolQueryBuilder catQuerys=QueryBuilders.boolQuery();

            if(cat.length>=1){
                catQuerys=catQuerys.must(QueryBuilders.termQuery("cats.name",cat[0]));
            }
            if(cat.length>=2){
               catQuerys= catQuerys.must(QueryBuilders.termQuery("cats.childs.name",cat[1]));

            }

            if(cat.length>=3){
                catQuerys=catQuerys.must(QueryBuilders.termQuery("cats.childs.childs.name",cat[2]));

            }
            return catQuerys;


        }else{

            return QueryBuilders.matchAllQuery();

        }

    }


    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Map<String, Object> add() {


        QueryBuilder qb = QueryBuilders.rangeQuery("costPrice").from(0).to(80);

//        QueryBuilder QB2= QueryBuilders
//        QueryBuilder queryBuilder=QueryBuilders.matchPhraseQuery("title","安踏女鞋");
//        QueryBuilder qbq=QueryBuilders.termQuery("brand","对克");

//        elasticAccountInfoRepository.search(qb)

        SearchResponse sResponse = elasticsearchTemplate.getClient().prepareSearch("shop")
                .setTypes("Product")
                // 设置search type
                // 常用search type用：query_then_fetch
                // query_then_fetch是先查到相关结构，然后聚合不同node上的结果后排序
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                // 查询的termName和termvalue
                .setQuery(qb)
//                        setQuery(qbq)
                // 设置排序field
                .addSort("costPrice", SortOrder.DESC)
                // 设置分页
                .setFrom(0).setSize(60).execute().actionGet();

        SearchHits hits = sResponse.getHits();

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("shop")
                .withTypes("Product")
                .withQuery(qb)
                .build();
        Page<GoodsInfo> search = elasticsearchTemplate.queryForPage(searchQuery, GoodsInfo.class);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("result", search);
        return result;


    }


}
