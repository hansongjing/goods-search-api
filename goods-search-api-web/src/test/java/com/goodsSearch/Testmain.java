package com.goodsSearch;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by hanhansongjiang on 17/5/26.
 */


    public class Testmain {
        public synchronized void test1(){

        }

        public void test2(){
            synchronized (this){

            }
        }
    }


