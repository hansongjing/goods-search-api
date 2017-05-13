package com.goodsSearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by hanhansongjiang on 17/5/12.
 */

@PropertySource("classpath:application.properties")
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.goodsSearch.Repository")
public class TestApplication {
    public static void main(String args[]){
        SpringApplication.run(TestApplication.class,args);
    }
}


