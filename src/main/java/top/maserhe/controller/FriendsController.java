package top.maserhe.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.maserhe.pojo.vo.FriendsVO;
import top.maserhe.service.FriendsService;
import top.maserhe.utils.result.JsonResult;

import java.util.List;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-30 17:13
 */
@RestController
public class FriendsController {

    @Autowired
    private FriendsService friendsService;

    // 这个在userController 写过了，
   /* @PostMapping("/search")
    public JsonResult searchFriend(String myUserId, String friendUsername) {

        // 1. 首先判断是否为空。
        if (StringUtils.isBlank(myUserId) || StringUtils.isBlank(friendUsername)) {
             return JsonResult.failure("参数为空");
         }



        return JsonResult.success();
    }*/

    @PostMapping("/myFriends")
    public JsonResult getFriendVOList(String userid) {

        // 1. 判断是否为空
        if (StringUtils.isBlank(userid)) return JsonResult.failure("参数错误");
        List<FriendsVO> friendsVOS = friendsService.queryFriendList(userid);

        if (friendsVOS == null) {


        }

        return JsonResult.success();
    }

}
