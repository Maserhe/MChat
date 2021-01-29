package top.maserhe.service;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-29 21:59
 */
public interface FriendsRequestService {

    /**
     * 发送好友请求
     * @param myUserId
     * @param friendUsername
     */
    public void sendFriendRequest(String myUserId, String friendUsername);


}
