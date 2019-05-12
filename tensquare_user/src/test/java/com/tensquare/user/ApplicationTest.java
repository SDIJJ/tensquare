package com.tensquare.user;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @author: Arnold
 * @since: 2019/5/11 14:56
 * @version: v1.0.0
 */
@SpringBootTest(classes = UserApplication.class )
@RunWith(SpringRunner.class)
public class ApplicationTest {
    @Before
    public void before() {
        System.out.println("#############测试开始#############");
    }
/*    @Test
    public void test(){
        System.out.println("j-------------------");
    }*/
    @After
    public void after() {
        System.out.println("##############测试结束#############");
    }
}
