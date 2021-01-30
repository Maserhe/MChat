package top.maserhe.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.maserhe.enums.RequestStatus;
import top.maserhe.service.FriendsRequestService;
import top.maserhe.service.FriendsService;
import top.maserhe.service.UserService;
import top.maserhe.utils.result.JsonResult;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-29 23:58
 */

@RestController
public class FriendsRequestController {

    @Autowired
    private UserService userService;
    @Autowired
    private FriendsRequestService friendsRequestService;

    @Autowired
    private FriendsService friendsService;
    /**
     * 获取 申请添加好友的列表。
     * @param userId
     * @return
     */
    @PostMapping("/queryFriendRequests")
    public JsonResult queryFriendsRequestList(String userId){

        // 1. 判断 不能为空
        if (StringUtils.isBlank(userId)) {
            return JsonResult.failure("");
        }

        // 2. 查询接收的用户请求
        return JsonResult.success(userService.queryMyFriendsList(userId));
    }

    @PostMapping("/operFriendRequest")
    public JsonResult operFriendRequest(String userId, String  senderId, Integer operType) {

        // 0. 判断不能为空
        if (StringUtils.isBlank(userId)
                || StringUtils.isBlank(senderId)
                || StringUtils.isBlank(operType.toString())) {
            return JsonResult.failure("");
        }
        // 1. 操作类型码错误。没有对应的枚举
        if (StringUtils.isBlank(RequestStatus.getMsgByKey(operType))) {
            return JsonResult.failure("操作类型码错误");
        }
        // 2. 查询用户
        if (operType == RequestStatus.ALREADY_IGNORE.getStatus()) {
            // 忽略用户请求。
            friendsRequestService.deleteFriendRequest(senderId, userId);
            return JsonResult.success("");
            // 通过请求。
        } else if (operType == RequestStatus.ALREADY_PASS.getStatus()){
            // 通过请求需要 添加两条记录。在好友表中。
            friendsRequestService.deleteFriendRequest(senderId, userId);
            friendsService.addFriends(userId, senderId);
        }
        return JsonResult.success("添加好友成功");
    }

}
