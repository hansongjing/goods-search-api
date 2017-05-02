package com.goodsSearch.config.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by jinwei on 25/4/2016.
 */

@ConfigurationProperties(prefix = "elasticsearch")
@Data
public class ElasticsearchProperties {
    private String clustername="my-application";

    private String host="localhost";

    private int port=9300;


}
