package top.maserhe.service;

import top.maserhe.entity.ChatMsg;
import top.maserhe.entity.bo.ChatMsgBo;

import java.util.List;

/**
 * @author Maserhe
 * @description
 * @date 2021/1/12 16:25
 */
public interface ChatMsgService {

    /**
     * 保存消息内容
     * @param chatMsgBo 前端传来的消息
     * @return 返回消息的ID
     */
    ChatMsg saveMsg(ChatMsgBo chatMsgBo);

    /**
     * 对消息进行批量签收
     * @param ids 消息ID集合
     */
    void batchSignMsg(List<String> ids);

    /**
     * 获取未签收的消息
     * @param acceptId 接收者id
     * @return List<ChatMsg>
     */
    List<ChatMsg> getUnsignedMsg(String acceptId);
}