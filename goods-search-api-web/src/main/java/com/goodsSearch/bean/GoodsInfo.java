package com.goodsSearch.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.goodsSearch.bean.cat.Cat;
import com.goodsSearch.bean.cat.Props;
import com.goodsSearch.bean.cat.Spec;
import com.goodsSearch.bean.cat.Stock;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;

import java.util.Date;
import java.util.List;

/**
 * Created by hanhansongjiang on 17/4/28.
 */
//{"typeId":2100,"updateTime":"2016-01-05T14:36:42+0800","productLine":[],"weight":"","D2PDisplayArea":[],"spuId":"33189","D2PDisplayLevel":[],"standardCateStatus":0,"gorder":15,"marketPrice":0,"D2POnSale":false,"d2cCommentCount":0,"d2cSaleCount":0,"images":[],"containerUnit":"","pointUserId":null,"specs":[{"specialPropName":"尺码（服装类）","svid":"66594","specialValName":"M ","spid":"22866"},{"specialPropName":"颜色","svid":"66602","specialValName":"黄色","spid":"22867"},{"specialPropName":"尺码（裤装）","svid":"66607","specialValName":"M","spid":"22868"}],"unit":"","skuImageLength":0,"spuImageLength":0,"d2cSort":null,"title":"对克 儿童+成人版篮球服206 M  黄色 M","salePoint":"","addTime":"2016-01-05T14:36:42+0800","top":0,"stdProductId":"p7679","P2COnSale":false,"custom":0,"standardParentCatePath":"","unitConversionNum":null,"adminId":"A000000","spuD2cSort":null,"d2cIntegral":0,"props":[{"name":"性别","value":"男"},{"name":"上装款式","value":"套头"},{"name":"上衣领型","value":"鸡心领"},{"name":"袖长","value":"无袖"},{"name":"裤长","value":"短裤"},{"name":"材质","value":"聚酯纤维"},{"name":"功能","value":"吸湿排汗"}],"d2pLabel":null,"d2pBlockedLevels":[],"type":"运动套装","noticeStock":null,"stock":{"status":true,"name":"总量","stock":0},"skuId":"g59338","spuAddTime":"2016-01-05T14:36:42+0800","SpuD2pSort":null,"tags":[],"cats":[{"childs":[{"childs":[],"name":"运动套装","id":"c1265"}],"name":"服装鞋包","id":"c101"}],"brand":"对克","costPrice":0,"levels":[],"productName":"对克 儿童+成人版篮球服206","sys":0,"spuUpdateTime":"2016-01-05T14:36:42+0800","fromOwner":1,"proxy":false,"supplyMarketOnSale":false,"brandId":6987,"D2PPrepay":false,"minStock":-1,"onSale":false,"minOrderQuantity":1,"d2pIntegral":0,"D2pSort":null,"shopStatus":1,"skuBn":"1975834662243782","barCode":"","D2COnSale":false,"d2cBlockedLevels":[],"catLines":[],"d2pNegativeStock":null,"salePrice":0,"creatorId":"A000000","standardCateId":"c1265","ownerId":"A000000","D2PDisplayUser":[]}; line: 1, column: 257] (through reference chain: com.goodsSearch.bean.GoodsInfo["images"])

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "shopGoods", type = "Product", shards = 1, replicas = 0, refreshInterval = "-1")
public class GoodsInfo {


    @Id
    private String skuId;
    private boolean D2COnSale;
    private boolean D2POnSale;

    private boolean D2PPrepay;

    private Date addTime;

    private String adminId;

    private String barCode;


   @Field(index = FieldIndex.not_analyzed)
    private String brand;


    private long brandId;

    private List<Cat> cats;

    private String containerUnit;


    private long costPrice;

    private String creatorId;

    private long custom;

    private long d2cCommentCount;

    private long d2cIntegral;

    private long d2cSaleCount;

    private long d2pIntegral;

    private long gorder;

    private List<String> images;

    private long marktPrice;


    private long minOrderQuantity;

    private long minStock;
    private boolean onSale;


    private String ownId;

    private String productName;

    private List<Props> props;


    private boolean proxy;

    private String salePoint;

    private long salePrice;


    private long shopStatus;

    private String skuBn;


    private long skuImageLength;

    private List<Spec> specs;


    private Date spuAddTime;

    private String spuId;

    private long spuImageLength;

    private Date spuUpdateTime;

    private String standardCateId;

    private long standardCateStatus;

    private String standardParentCatePath;

    private String stdProductId;

    private Stock stock;


    private boolean supplyMarketOnSale;


    private long sys;

    private List<String> tags;

    private String title;

    private long top;

    private String type;

    private long TypeId;

    private String unit;

    private Date updateTime;

    private String weight;

    private List<String> productLine;


    private List<String> D2PDisplayArea;





}
