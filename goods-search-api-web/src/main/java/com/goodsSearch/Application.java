package com.goodsSearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by hanhansongjiang on 17/4/27.
 */
@EnableAspectJAutoProxy
@SpringBootApplication(exclude = {ElasticsearchAutoConfiguration.class})
public class Application {


    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }


}
