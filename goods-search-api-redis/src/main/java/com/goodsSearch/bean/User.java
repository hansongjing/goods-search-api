package com.goodsSearch.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by hanhansongjiang on 17/5/12.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
public class User implements Serializable {

   @Id
    private  String id;

    private String  name;


    private String address;


    private Integer age;
}
