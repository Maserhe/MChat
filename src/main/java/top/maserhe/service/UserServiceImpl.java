package top.maserhe.service;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.maserhe.mapper.UserMapper;
import top.maserhe.pojo.User;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-26 2:36
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean queryUsernameIsExist(String username) {
        User user = userMapper.getUserByUsername(username);
        return user != null? true: false;
    }

    @Override
    public User queryUserForLogin(String username, String password) {
        return userMapper.gerUserByUsernameAndPassword(username, password);
    }

    @Override
    public User saveUser(User user) {
        // 创建唯一的 sid
        user.setId(Sid.next());
        // 给用户创建 二维码
        user.setCid("");
        userMapper.insert(user);

        return user;
    }
}
