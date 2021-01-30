package top.maserhe.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.maserhe.pojo.FriendsRequest;

@Mapper
public interface FriendsRequestMapper {
    int deleteByPrimaryKey(String id);

    int insert(FriendsRequest record);

    int insertSelective(FriendsRequest record);

    FriendsRequest selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FriendsRequest record);

    int updateByPrimaryKey(FriendsRequest record);

    FriendsRequest queryFriendsRequestByBothId(String myUserId, String friendId);

    int deleteBySenderUserIdAndAcceptUserId(String senderUserId, String acceptUserId);
}