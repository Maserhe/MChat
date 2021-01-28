package top.maserhe.utils.result;

import org.springframework.beans.BeanUtils;
import top.maserhe.pojo.User;
import top.maserhe.pojo.vo.UserVO;

/**
 * @author monkJay
 * @description
 * @date 2020/1/10 15:20
 */
public class ReturnVOUtil {

    public static UserVO copyToUsersVO(User user){
        // 将需要的信息封装到VO类中
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }
}