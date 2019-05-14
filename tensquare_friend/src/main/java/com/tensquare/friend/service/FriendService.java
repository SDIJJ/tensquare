package com.tensquare.friend.service;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

/**
 * @Description:
 * @author: Arnold
 * @since: 2019/5/13 10:21
 * @version: v1.0.0
 */
@Service
@Transactional
public class FriendService {
    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;

    @Autowired
    private UserClient userClient;

    @Autowired
    private IdWorker idWorker;

    public int addFriend(String userId, String friendId) {
        //1.判断重复添加
        Friend friend = friendDao.findFriendByUseridAndFriendid(userId, friendId);
        if (friend != null)
            return 0;
        //2.判断friendId->userId 的状态
        if (friendDao.findFriendByUseridAndFriendid(friendId, userId) != null) {
            friendDao.updateIslike("1", userId, friendId);
            friendDao.updateIslike("1", friendId, userId);
        } else {
            Friend f = new Friend();
            f.setFriendid(friendId);
            f.setUserid(userId);
            f.setIslike("0");
            friendDao.save(f);
        }
        return 1;
    }

    public int addNoFriend(String userId, String friendId) {
        NoFriend noFriend = noFriendDao.findFriendByUseridAndFriendid(userId, friendId);
        if (noFriend != null)
            return 0;
        NoFriend nf = new NoFriend();
        nf.setFriendid(friendId);
        nf.setUserid(userId);
        noFriendDao.save(nf);
        return 1;
    }

    public void deleteFriend(String userId, String friendId) {
        //删除关系
        friendDao.deleteFriend(userId, friendId);
        //值为单项好友或操作无效
        friendDao.updateIslike("0", friendId, userId);
        /**
         * 此步操作有疑问
         */
        NoFriend noFriend = new NoFriend();
        noFriend.setUserid(userId);
        noFriend.setFriendid(friendId);
        noFriendDao.save(noFriend);
    }
}
