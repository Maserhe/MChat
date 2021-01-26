package top.maserhe.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.maserhe.pojo.ChatMessage;

@Mapper
public interface ChatMessageMapper {
    int deleteByPrimaryKey(String id);

    int insert(ChatMessage record);

    int insertSelective(ChatMessage record);

    ChatMessage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ChatMessage record);

    int updateByPrimaryKey(ChatMessage record);
}