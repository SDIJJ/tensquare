package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Description:
 * @author: Arnold
 * @since: 2019/5/13 10:42
 * @version: v1.0.0
 */
public interface FriendDao extends JpaRepository<Friend, String> {
    Friend findFriendByUseridAndFriendid(String userid, String friendid);

    @Modifying
    @Query(value = "update tb_friend set islike=? where userid=? and friendid=?", nativeQuery = true)
    void updateIslike(String islike, String usrid, String friendid);

    @Modifying
    @Query(value = "delete from  tb_friend where userid=? and friendid=?", nativeQuery = true)
    void deleteFriend(String userId, String friendId);
}
