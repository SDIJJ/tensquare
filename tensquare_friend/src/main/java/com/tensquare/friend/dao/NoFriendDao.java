package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Description:
 * @author: Arnold
 * @since: 2019/5/13 10:42
 * @version: v1.0.0
 */
public interface NoFriendDao extends JpaRepository<NoFriend, String> {
    NoFriend findFriendByUseridAndFriendid(String userid, String friendid);
}
