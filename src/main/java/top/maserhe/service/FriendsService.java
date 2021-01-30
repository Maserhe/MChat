package top.maserhe.service;

import top.maserhe.pojo.Friends;
import top.maserhe.pojo.User;
import top.maserhe.pojo.vo.FriendsVO;

import java.util.List;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-30 2:24
 */
public interface FriendsService {

    Friends addFriends(String userId, String friendUserId);

    List<FriendsVO> queryFriendList(String userId);
}
