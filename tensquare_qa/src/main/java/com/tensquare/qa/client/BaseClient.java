package com.tensquare.qa.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description:
 * @author: Arnold
 * @since: 2019/5/13 8:56
 * @version: v1.0.0
 */
@FeignClient(value = "tensquare-base", fallback = BaseClientImpl.class)
public interface BaseClient {
    @GetMapping("/label/{labelId}")
    Result findById(@PathVariable("labelId") String labelId);

}
