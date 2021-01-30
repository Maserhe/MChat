package top.maserhe.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.maserhe.pojo.Friends;
import top.maserhe.pojo.vo.FriendsVO;

import java.util.List;

@Mapper
public interface FriendsMapper {

    int deleteByPrimaryKey(String id);

    int insert(Friends record);

    int insertSelective(Friends record);

    Friends selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Friends record);

    int updateByPrimaryKey(Friends record);

    Friends searchFriend(String myId, String friendId);

    List<FriendsVO> queryFriendList(String userId);
}