package top.maserhe.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.maserhe.pojo.User;
import top.maserhe.pojo.vo.UserVo;
import top.maserhe.service.UserService;
import top.maserhe.utils.MD5Utils;
import top.maserhe.utils.MaserheJSONResult;

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

    // 暂时注册 和 登陆是一个 请求
    @RequestMapping("/login")
    public MaserheJSONResult registerOrLogin(@RequestBody User user) throws Exception {

        System.out.println(user);
        // 用户名和 密码 不能位空
        if (StringUtils.isEmpty(user.getUsername())|| StringUtils.isEmpty(user.getPassword())) {
            return MaserheJSONResult.errorMsg("用户名和密码 不能位空。。。。。");
        }
        // 判断用户名是否存在， 存在就登陆，否则就注册。
        User userResult = null;
        if (userService.queryUsernameIsExist(user.getUsername())) {
            // 登陆。
            userResult = userService.queryUserForLogin(user.getUsername(),MD5Utils.getMD5Str(user.getPassword()));
            if (userResult == null) {
                return MaserheJSONResult.errorMsg("用户名或者密码不正确");
            }
        }
        else {

            //  注册。
            user.setNickname(user.getUsername());
            user.setFaceImage("");
            user.setFaceImageBig("");
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));

            userResult = userService.saveUser(user);

        }
        UserVo uservo =  new UserVo();
        BeanUtils.copyProperties(userResult, uservo);
        return MaserheJSONResult.ok(uservo);
    }

    @RequestMapping("/ni")
    public String todo() {
        return "ni";
    }

}
