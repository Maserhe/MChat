package top.maserhe.mapper;

import org.apache.ibatis.annotations.Mapper;
import sun.misc.Request;
import top.maserhe.pojo.Friends;
import top.maserhe.pojo.User;
import top.maserhe.pojo.vo.FriendRequestVO;
import top.maserhe.pojo.vo.UserVO;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    // 上面为逆向工具生成。
    User getUserByUsername(String username);

    User gerUserByUsernameAndPassword(String username, String password);

    List<FriendRequestVO> queryMyFriendsList(String userId);

}