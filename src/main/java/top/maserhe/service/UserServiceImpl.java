package top.maserhe.service;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User saveUser(User user) {
        // 创建唯一的 sid
        user.setId(Sid.next());
        // 给用户创建 二维码
        user.setQrcode("");
        userMapper.insert(user);
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User updateUserFaceImage(User user) {
        userMapper.updateByPrimaryKeySelective(user);
        return queryUserById(user.getId());
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public User queryUserById(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }


}
