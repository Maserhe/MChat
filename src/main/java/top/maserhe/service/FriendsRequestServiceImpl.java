package top.maserhe.service;

import org.n3r.idworker.Sid;
import org.springframework.stereotype.Service;
import top.maserhe.mapper.FriendsMapper;
import top.maserhe.mapper.FriendsRequestMapper;
import top.maserhe.pojo.Friends;
import top.maserhe.pojo.FriendsRequest;
import top.maserhe.pojo.User;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-29 22:00
 */
@Service
public class FriendsRequestServiceImpl implements FriendsRequestService{


    @Resource
    private UserService userService;

    @Resource
    private FriendsRequestMapper friendsRequestMapper;

    @Override
    public void sendFriendRequest(String myUserId, String friendUsername) {
        // 0. 把朋友查出来。
        User user = userService.queryUsersByUsername(friendUsername);
        // 1. 查询发送好友记录表。 根据我和朋友的id;
        FriendsRequest friendsRequest = friendsRequestMapper.queryFriendsRequestByBothId(myUserId, user.getId());
        // 2. 两个人不是朋友。
        if (friendsRequest == null) {
            friendsRequest = new FriendsRequest();
            // 新增加好友请求。
            String requestId = Sid.next();
            friendsRequest.setId(requestId);
            friendsRequest.setSendUserId(myUserId);
            friendsRequest.setAcceptUserId(user.getId());
            friendsRequest.setRequestDateTime(new Date());
            // 添加记录
            friendsRequestMapper.insert(friendsRequest);
        }

    }
}
