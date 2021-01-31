package top.maserhe.service.impl;

import top.maserhe.entity.MyFriends;
import top.maserhe.entity.Users;
import top.maserhe.entity.vo.FriendsVO;
import top.maserhe.entity.vo.UsersVO;
import top.maserhe.enums.MsgAction;
import top.maserhe.enums.SearchFriendsStatus;
import top.maserhe.mapper.MyFriendsMapper;
import top.maserhe.netty.MsgContent;
import top.maserhe.netty.UserChannelRel;
import top.maserhe.service.FriendService;
import top.maserhe.utils.JsonUtil;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.n3r.idworker.Sid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Maserhe
 * @description
 * @date 2021/1/10 14:46
 */
@Service
public class FriendServiceImpl implements FriendService {

    @Resource
    MyFriendsMapper myFriendsMapper;

    @Resource
    FriendRequestServiceImpl friendRequestService;

    @Resource
    UserServiceImpl userService;

    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public Integer preconditionSearchFriends(String myUserId, String friendUsername) {
        Users users = userService.queryUsersByUsername(friendUsername);
        // 用户不存在
        if (users == null){
            return SearchFriendsStatus.USER_NOT_EXIST.status;
        }
        // 搜素的账号是自己
        if (users.getId().equals(myUserId)){
            return SearchFriendsStatus.NOT_YOURSELF.status;
        }
        Example example = new Example(MyFriends.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("myUserId", myUserId);
        criteria.andEqualTo("myFriendUserId", users.getId());
        MyFriends myFriends = myFriendsMapper.selectOneByExample(example);
        // 该账号已经是我的好友
        if (myFriends != null){
            return SearchFriendsStatus.ALREADY_FRIEND.status;
        }
        return SearchFriendsStatus.SUCCESS.status;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void passFriendRequest(FriendsVO friendsVO) {
        // 将朋友保存为我的好友，并设置朋友给我的备注
        saveFriend(friendsVO.getUserId(), friendsVO.getFriendId(), friendsVO.getMyRemark());
        // 将我保存为朋友的好友，并设置我给朋友的备注
        saveFriend(friendsVO.getFriendId(), friendsVO.getUserId(), friendsVO.getFriendRemark());
        // 更新请求状态为通过
        friendRequestService.updateRequestStatus(friendsVO);
        // 使用websocket主动推送消息到请求发起者，使他拉取最新的好友列表
        Channel senderChannel = UserChannelRel.get(friendsVO.getFriendId());
        if (senderChannel != null) {
            MsgContent msgContent = new MsgContent();
            msgContent.setAction(MsgAction.PULL_FRIEND.type);
            senderChannel.writeAndFlush(
                    new TextWebSocketFrame(JsonUtil.convertToJson(msgContent)));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveFriend(String userId, String friendId, String remark) {
        MyFriends myFriends = new MyFriends();
        myFriends.setId(Sid.nextShort());
        myFriends.setMyUserId(userId);
        myFriends.setMyFriendUserId(friendId);
        // 设置好友的备注
        myFriends.setFriendRemark(remark);
        // 保存我的好友
        myFriendsMapper.insert(myFriends);
    }

    @Override
    public List<UsersVO> queryMyFriendsList(String userId) {
        return myFriendsMapper.queryMyFriendsList(userId);
    }
}