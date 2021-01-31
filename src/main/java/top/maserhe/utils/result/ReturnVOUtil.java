package top.maserhe.utils.result;

import top.maserhe.entity.Users;
import top.maserhe.entity.vo.UsersVO;
import org.springframework.beans.BeanUtils;

/**
 * @author Maserhe
 * @description
 * @date 2021/1/10 15:20
 */
public class ReturnVOUtil {

    public static UsersVO copyToUsersVO(Users users){
        // 将需要的信息封装到VO类中
        UsersVO usersVO = new UsersVO();
        BeanUtils.copyProperties(users, usersVO);
        return usersVO;
    }
}