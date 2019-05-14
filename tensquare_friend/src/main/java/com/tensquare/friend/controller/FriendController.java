package com.tensquare.friend.controller;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @author: Arnold
 * @since: 2019/5/13 10:03
 * @version: v1.0.0
 */
@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    private FriendService friendService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserClient userClient;

    /**
     * 添加好友，拉黑好友
     *
     * @param friendId
     * @param type     1：喜欢  2：不喜欢
     * @return
     */
    @PutMapping("/like/{friendId}/{type}")
    public Result addFriend(@PathVariable String friendId, @PathVariable String type) {
        Claims user_claims = (Claims) request.getAttribute("user_claims");
        if (user_claims == null)
            return new Result(false, StatusCode.LOGINERROR, "请先登录");
        String userId = user_claims.getId();

        //1.验证身份
        //2.好友操作
        if (!StringUtils.isEmpty(type) && type.equals("1")) {
            int res = friendService.addFriend(userId, friendId);
            if (res == 1) {
                userClient.updateFanscountAndFollowcount(userId,friendId,1);
                return new Result("添加好友成功");
            } else if (res == 0) {
                return new Result(false, StatusCode.ERROR, "不能重复添加好友");
            } else {
                return new Result(false, StatusCode.ERROR, "其他错误");
            }
        } else if (!StringUtils.isEmpty(type) && type.equals("2")) {
            int res =friendService.addNoFriend(userId,friendId);
            if (res == 1) {
                return new Result("添加非好友成功");
            }else if(res==0){
                return new Result(false, StatusCode.ERROR, "不能重复添加非好友");
            }else{
                return new Result(false, StatusCode.ERROR, "其他错误");
            }
        } else
            return new Result(false, StatusCode.ERROR, "参数错误");

    }

    @DeleteMapping("/{friendId}")
    public Result deleteFriend(@PathVariable String friendId) {
        Claims user_claims = (Claims) request.getAttribute("user_claims");
        if (user_claims == null)
            return new Result(false, StatusCode.LOGINERROR, "请先登录");
        String userId = user_claims.getId();
        friendService.deleteFriend(userId,friendId);
        userClient.updateFanscountAndFollowcount(userId,friendId,-1);
        return new Result("删除成功");
    }
}
