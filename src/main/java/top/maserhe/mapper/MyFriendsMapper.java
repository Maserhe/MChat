package top.maserhe.mapper;

import top.maserhe.entity.MyFriends;
import top.maserhe.entity.vo.UsersVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Maserhe
 * @description
 * @date 2021/1/7 19:51
 */
@Mapper
public interface MyFriendsMapper extends tk.mybatis.mapper.common.Mapper<MyFriends> {

    /**
     * 查找我的好友列表
     * @param userId 我的账号id
     * @return 好友列表
     */
    @Select("SELECT u.* from users u left join my_friends mf " +
            "on u.id = mf.my_friend_user_id where mf.my_user_id = #{userId}")
    List<UsersVO> queryMyFriendsList(String userId);
}