package top.maserhe.service;

import top.maserhe.entity.vo.FriendsVO;
import top.maserhe.entity.vo.UsersVO;

import java.util.List;

/**
 * @author Maserhe
 * @description
 * @date 2021/1/10 14:46
 */
public interface FriendService {
    /**
     * 搜索好友的前置条件(在枚举类中定义的)
     * @param myUserId 我的账号id
     * @param friendUsername 好友账号
     * @return 前置条件
     */
    Integer preconditionSearchFriends(String myUserId, String friendUsername);

    /**
     * 通过好友的请求
     * @param friendsVO FriendsVO
     */
    void passFriendRequest(FriendsVO friendsVO);

    /**
     * 查找我的好友列表
     * @param userId 我的账号id
     * @return 好友列表
     */
    List<UsersVO> queryMyFriendsList(String userId);
}
