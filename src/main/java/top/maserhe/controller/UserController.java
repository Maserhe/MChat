package top.maserhe.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.maserhe.enums.ResultCode;
import top.maserhe.pojo.User;
import top.maserhe.service.UserService;
import top.maserhe.utils.Md5Util;
import top.maserhe.utils.result.JsonResult;
import top.maserhe.utils.result.ReturnVOUtil;


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

    @PostMapping("/login")
    public JsonResult toLogin(@RequestBody User user) {


        System.out.println(user);
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
            } else return JsonResult.success(userResult);
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

            return JsonResult.success(user);
        }
    }
}
