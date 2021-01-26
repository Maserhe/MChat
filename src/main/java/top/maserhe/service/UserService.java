package top.maserhe.service;

import top.maserhe.pojo.User;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-26 2:34
 */
public interface UserService {

    // 查询 用户名 是否 存在。
    public boolean queryUsernameIsExist(String username);

    // 验证用户的 登陆信息
    public User queryUserForLogin(String username, String password);

    // 注册用户
    public User saveUser(User user);
}
