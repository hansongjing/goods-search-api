/**
 * Company
 * Copyright (C) 2014-2017 All Rights Reserved.
 */
package com.goodsSearch.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * @author cwenao
 * @version $Id AccountInfo.java, v 0.1 2017-02-06 10:28 cwenao Exp $$
 */
@Data
@Document(indexName = "goods",type = "Goods", shards = 1,replicas = 0, refreshInterval = "-1")
public class AccountInfo implements Serializable{



    @Id
    private String id;
    @Field
    private String accountName;
    @Field
    private String nickName;

    public AccountInfo(String id, String accountName, String nickName) {
        this.id = id;
        this.accountName = accountName;
        this.nickName = nickName;
    }

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */

    public String getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     *
     * @param id value to be assigned to property id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>accountName</tt>.
     *
     * @return property value of accountName
     */

    public String getAccountName() {
        return accountName;
    }

    /**
     * Setter method for property <tt>accountName</tt>.
     *
     * @param accountName value to be assigned to property accountName
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * Getter method for property <tt>nickName</tt>.
     *
     * @return property value of nickName
     */

    public String getNickName() {
        return nickName;
    }

    /**
     * Setter method for property <tt>nickName</tt>.
     *
     * @param nickName value to be assigned to property nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}

