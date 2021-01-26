package top.maserhe.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.maserhe.pojo.User;

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

}