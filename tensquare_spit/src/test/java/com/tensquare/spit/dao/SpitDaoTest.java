package com.tensquare.spit.dao;

import com.tensquare.spit.SpitApplication;
import com.tensquare.spit.pojo.Spit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description: 测试mongodb->@Query
 * @author: Arnold
 * @since: 2019/5/10 12:33
 * @version: v1.0.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@Rollback(false)
public class SpitDaoTest {

    @Test
    public void testQuery() {

        System.out.println("============================");
    }

}