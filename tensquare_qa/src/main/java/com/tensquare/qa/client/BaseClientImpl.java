package com.tensquare.qa.client;

import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @author: Arnold
 * @since: 2019/5/13 20:54
 * @version: v1.0.0
 */
@Component
public class BaseClientImpl implements BaseClient {
    @Override
    public Result findById(String labelId) {
        return new Result(false, StatusCode.ERROR,"熔断器出发");
    }
}
