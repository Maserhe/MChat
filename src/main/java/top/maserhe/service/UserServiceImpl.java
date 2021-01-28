package top.maserhe.service;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import top.maserhe.component.FastdfsClient;
import top.maserhe.mapper.UserMapper;
import top.maserhe.pojo.User;
import top.maserhe.utils.LogUtil;
import top.maserhe.utils.file.Base64DecodeMultipartFile;
import top.maserhe.utils.file.FileUtil;
import top.maserhe.utils.result.qrcode.QRCodeUtil;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

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

    @Resource
    FastdfsClient fastdfsClient;

    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public boolean queryUsernameIsExist(String username) {
        User user = userMapper.getUserByUsername(username);
        return user != null? true: false;
    }
    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public User queryUserForLogin(String username, String password) {
        return userMapper.gerUserByUsernameAndPassword(username, password);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public User saveUser(User user) {
        // 创建唯一的 sid
        user.setId(Sid.next());
        // 给用户创建 二维码
        user.setQrcode(createQrcode(user, null));
        userMapper.insert(user);
        return user;
    }

    public User updateUserFaceImage(User user) {
        userMapper.updateByPrimaryKeySelective(user);
        return queryUserById(user.getId());
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    public User queryUserById(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }



    @Transactional(rollbackFor = Exception.class)
    public User updateUserInfo(User user) {
        // User user1 = queryUserById(user.getId());
        // 如果头像修改了，就修改相应的二维码的logo
        /*if (user.getFaceImage() != null && !user.getFaceImage().equals(user1.getFaceImage())){
            user.setQrcode(createQrcode(user1, user.getFaceImage()));
        }*/
        userMapper.updateByPrimaryKeySelective(user);
        return queryUserById(user.getId());
    }

    /**
     * 生成一个二维码，内容是账号id
     * @param users 账号信息
     * @param logoUrl logo的网络链接
     * @return 生成的二维码网络链接
     */
    private String createQrcode(User users, String logoUrl){
        String qrcode = null;
        try {
            // 设置二维码生成的位置
            String qrCodePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\qrcode\\" + users.getId() + "qrcode.png";
            // 设置二维码的内容
            String content = "chat_qrcode:" + users.getUsername();
            // 如果没有设置logo，就使用默认的
            if (logoUrl == null){
                logoUrl = "https://img-blog.csdnimg.cn/20210129031819417.jpg";
            }
            // 生成二维码
            QRCodeUtil.encodeQRCode(content, qrCodePath, logoUrl);
            // 获取文件的字节流
            byte[] bytes = Files.readAllBytes(Paths.get(qrCodePath));
            // 将文件转换为base64
            String base64 = "data:image/png;base64," + Base64.getEncoder().encodeToString(bytes);
            // base64再转换为multipartFile对象
            MultipartFile multipartFile = Base64DecodeMultipartFile.base64ToMultipartFile(base64);
            // 将二维码图片上传到服务器
            qrcode = FileUtil.IMG_SERVER_URL + fastdfsClient.uploadQRCode(multipartFile);

            // 删除本地文件
            Files.delete(Paths.get(qrCodePath));
        } catch (IOException e) {
            LogUtil.error(e.getMessage(), e);
        }
        return qrcode;
    }
}
