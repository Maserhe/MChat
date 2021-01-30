package top.maserhe.controller;

import com.mysql.cj.util.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import top.maserhe.enums.ResultCode;
import top.maserhe.enums.SearchFriendsStatus;
import top.maserhe.mapper.FriendsRequestMapper;
import top.maserhe.pojo.User;
import top.maserhe.pojo.bo.UserBO;
import top.maserhe.pojo.vo.UserVO;
import top.maserhe.service.FriendsRequestService;
import top.maserhe.service.FriendsRequestServiceImpl;
import top.maserhe.service.UserService;
import top.maserhe.utils.LogUtil;
import top.maserhe.utils.Md5Util;
import top.maserhe.utils.result.JsonResult;
import top.maserhe.utils.result.ReturnVOUtil;
import top.maserhe.utils.result.qrcode.LogoUtil;


/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-26 2:20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FriendsRequestService friendsRequestService;

    @PostMapping("/login")
    public JsonResult toLogin(@RequestBody User user) {

        // 0. 判断用户名和密码不能为空
        if (StringUtils.isBlank(user.getUsername())|| StringUtils.isBlank(user.getPassword())) {
            return JsonResult.failure(ResultCode.USER_OR_PWD_NULL);
        }
        // 1. 判断用户名是否存在，如果存在就登录，如果不存在则注册
        boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());
        User userResult;
        if (usernameIsExist) {
            // 1.1 登录
            userResult = userService.queryUserForLogin(user.getUsername(), Md5Util.getMd5(user.getPassword()));
            if (userResult == null) {
                return JsonResult.failure(ResultCode.USER_LOGIN_ERROR);
            } else {
                return JsonResult.success(ReturnVOUtil.copyToUsersVO(userResult));
            }
        } else {
            return JsonResult.failure(ResultCode.USER_NOT_EXIST);
        }

    }
    @PostMapping("/register")
    public JsonResult toRegister(@RequestBody User user) {


        // 0. 判断用户名和密码不能为空
        if (StringUtils.isBlank(user.getUsername())|| StringUtils.isBlank(user.getPassword())) {
            return JsonResult.failure(ResultCode.USER_OR_PWD_NULL);
        }

        // 1. 判断用户名是否存在，用户已经存在了。
        boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());
        if (usernameIsExist) {
            return JsonResult.failure(ResultCode.USER_HAS_EXISTED);
        } else {
            // 1.1 进行注册。
            User userResult;
            // 1.2 注册
            user.setNickname(user.getUsername());
            user.setFaceImage("");
            user.setFaceImageBig("");
            user.setPassword(Md5Util.getMd5(user.getPassword()));
            userResult = userService.saveUser(user);
            return JsonResult.success(ReturnVOUtil.copyToUsersVO(userResult));
        }
    }

    @PostMapping("/nickname")
    public JsonResult setNickname(@RequestBody UserBO userBo){
        // 更新用户昵称
        LogUtil.info("修改nickname"+ userBo);
        User user = new User();
        user.setId(userBo.getUserId());
        user.setNickname(userBo.getNickname());
        User result = userService.updateUserInfo(user);
        LogUtil.info(result.getNickname());
        return JsonResult.success(ReturnVOUtil.copyToUsersVO(result));
    }


    @PostMapping("/search")
    public JsonResult searchUser(String userId, String friendUsername) {

        // 1. 判断 userId friend 不能为空
        if (StringUtils.isBlank(friendUsername) || StringUtils.isBlank(friendUsername))
            return JsonResult.failure(ResultCode.PARAM_IS_BLANK);   // 参数为空。
        Integer status = userService.preconditionSearchFriends(userId, friendUsername);

        // 2. 两个人 还不是朋友。
        if (status == SearchFriendsStatus.SUCCESS.status) {
            User user = userService.queryUsersByUsername(friendUsername);
            // 将对象返回 userVo
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            return JsonResult.success(userVO);

        } else {
        // 3. 根据错误的状态码， 返回错误信息。
            String errorMsg = SearchFriendsStatus.getMsgByKey(status);
            return JsonResult.failure(errorMsg);
        }
    }

    /**
     *  添加用户
     * @param userId        本人id
     * @param friendUsername 需要添加好友的id
     * @return
     */
    @PostMapping("/addFriendRequest")
    public JsonResult addUser(String userId, String friendUsername) {

        // 1. 判断 userId friend 不能为空
        if (StringUtils.isBlank(friendUsername) || StringUtils.isBlank(friendUsername))
            return JsonResult.failure(ResultCode.PARAM_IS_BLANK);   // 参数为空。
        Integer status = userService.preconditionSearchFriends(userId, friendUsername);

        // 2. 两个人 还不是朋友。
        if (status == SearchFriendsStatus.SUCCESS.status) {
            // 发送好友请求。
            friendsRequestService.sendFriendRequest(userId, friendUsername);
            return JsonResult.success();
        } else {
            // 3. 根据错误的状态码， 返回错误信息。
            String errorMsg = SearchFriendsStatus.getMsgByKey(status);
            return JsonResult.failure(errorMsg);
        }
    }
}
