package com.tensquare.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @Description:
 * @author: Arnold
 * @since: 2019/5/13 10:34
 * @version: v1.0.0
 */
@FeignClient("tensquare-user")
public interface UserClient {
    @PutMapping("/user/update/{userId}/{friendId}/{count}")
    public void updateFanscountAndFollowcount(@PathVariable("userId") String userId,
                                              @PathVariable("friendId") String friendId,
                                              @PathVariable("count") int count);


}
