package top.maserhe.mapper;

import top.maserhe.entity.FriendsRequest;
import top.maserhe.entity.vo.FriendRequestVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author monkJay
 * @description
 * @date 2020/1/7 19:51
 */
@Mapper
public interface FriendsRequestMapper extends tk.mybatis.mapper.common.Mapper<FriendsRequest> {
}