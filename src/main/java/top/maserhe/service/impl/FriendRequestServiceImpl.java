package top.maserhe.service.impl;

import top.maserhe.entity.FriendsRequest;
import top.maserhe.entity.Users;
import top.maserhe.entity.vo.FriendRequestVO;
import top.maserhe.entity.vo.FriendsVO;
import top.maserhe.enums.RequestStatus;
import top.maserhe.mapper.CustomMsgMapper;
import top.maserhe.mapper.FriendsRequestMapper;
import top.maserhe.service.FriendRequestService;
import org.n3r.idworker.Sid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Maserhe
 * @description
 * @date 2021/1/11 0:10
 */
@Service
public class FriendRequestServiceImpl implements FriendRequestService {

    @Resource
    FriendsRequestMapper friendsRequestMapper;

    @Resource
    CustomMsgMapper customMsgMapper;

    @Resource
    UserServiceImpl userService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void sendFriendRequest(FriendRequestVO friendRequestVO) {
        // 查找好友的账号信息
        Users users = userService.queryUsersByUsername(friendRequestVO.getAcceptUsername());
        // 判断这个添加好友的请求是否存在
        FriendsRequest friendsRequest = queryRequestIsExist(friendRequestVO.getSendUserId(), users.getId());
        // 如果请求不存在，再进行下一步，否则直接结束
        if (friendsRequest == null){
            // 新建一个添加好友请求并插入数据库中
            FriendsRequest request = new FriendsRequest();
            request.setId(Sid.nextShort());
            request.setSendUserId(friendRequestVO.getSendUserId());
            request.setAcceptUserId(users.getId());
            request.setRequestDataTime(new Date());
            // 添加好友的验证信息
            request.setRequestMessage(friendRequestVO.getVerifyMessage());
            // 接收者的备注
            request.setAcceptRemark(friendRequestVO.getAcceptRemark());
            request.setRequestStatus(RequestStatus.WAITING_VERIFY.message);
            friendsRequestMapper.insert(request);
        }
    }

    private FriendsRequest queryRequestIsExist(String myUserId, String friendUserId){
        Example example = new Example(FriendsRequest.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("sendUserId", myUserId);
        criteria.andEqualTo("acceptUserId", friendUserId);
        return friendsRequestMapper.selectOneByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public List<FriendRequestVO> queryRequestByUserId(String userId) {
        return customMsgMapper.getRequestByUserId(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateRequestStatus(FriendsVO friendsVO) {
        Example example = new Example(FriendsRequest.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("sendUserId", friendsVO.getFriendId());
        criteria.andEqualTo("acceptUserId", friendsVO.getUserId());
        // 先根据条件获取到FriendsRequest对象
        FriendsRequest friendsRequest = friendsRequestMapper.selectOneByExample(example);
        // 更新请求的状态
        friendsRequest.setRequestStatus(RequestStatus.getMsgByKey(friendsVO.getType()));
        // 更新请求
        friendsRequestMapper.updateByExampleSelective(friendsRequest, example);
    }

    @Override
    public boolean queryRequestIsHandled(String userId, String friendId) {
        FriendsRequest friendsRequest = queryRequestIsExist(friendId, userId);
        // 如果请求存在，且请求已经被处理过
        if (friendsRequest != null &&
                !friendsRequest.getRequestStatus().equals(RequestStatus.WAITING_VERIFY.message)){
            return true;
        }
        return false;
    }
}