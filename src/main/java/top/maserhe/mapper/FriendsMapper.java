package top.maserhe.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.maserhe.pojo.Friends;

@Mapper
public interface FriendsMapper {

    int deleteByPrimaryKey(String id);

    int insert(Friends record);

    int insertSelective(Friends record);

    Friends selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Friends record);

    int updateByPrimaryKey(Friends record);

    Friends searchFriend(String myId, String friendId);

}