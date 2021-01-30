package top.maserhe.service;

import jdk.internal.dynalink.linker.LinkerServices;
import top.maserhe.pojo.User;
import top.maserhe.pojo.vo.FriendRequestVO;

import java.util.List;

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

    // 修改用户的 记录。
    public User updateUserFaceImage(User user);

    // 根据用户id查询用户。
    public User queryUserById(String userId);

    // 更新用户信息。
    User updateUserInfo(User user);

    // 根据用户名来查询用户。
    User queryUsersByUsername(String username);

    // 搜索朋友的前置条件。
    Integer preconditionSearchFriends(String userId, String  friendUsername);

    // 查询所有的好友申请。
    List<FriendRequestVO> queryMyFriendsList(String acceptUserId);

}
