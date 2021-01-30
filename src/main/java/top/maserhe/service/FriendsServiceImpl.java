package top.maserhe.service;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.maserhe.mapper.FriendsMapper;
import top.maserhe.pojo.Friends;
import top.maserhe.pojo.User;
import top.maserhe.pojo.vo.FriendsVO;

import java.util.List;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-30 2:26
 */
@Service
public class FriendsServiceImpl implements FriendsService{

    @Autowired
    FriendsMapper friendsMapper;

    /**
     * 添加好友 需要添加 两条记录。
     * @param userId
     * @param friendUserId
     * @return
     */
    @Override
    public Friends addFriends(String userId, String friendUserId) {
        Friends friends = new Friends();
        friends.setId(Sid.next());
        friends.setMyUserId(userId);
        friends.setMyFriendUserId(friendUserId);
        // 添加一条。
        friendsMapper.insert(friends);
        // 在添加一条。
        friends.setId(Sid.next());
        friends.setMyFriendUserId(userId);
        friends.setMyUserId(friendUserId);
        friendsMapper.insert(friends);

        return friends;
    }

    /**
     *  查询用户的 所有好友
     * @param userId
     * @return
     */
    @Override
    public List<FriendsVO> queryFriendList(String userId) {
        return friendsMapper.queryFriendList(userId);
    }
}
