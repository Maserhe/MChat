package top.maserhe.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * @author Maserhe
 * @description
 * @date 2021/1/12 16:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChatMsgBo {

    /**
     * 消息id
     */
    private String msgId;

    /**
     * 发送者的账号id
     */
    private String senderId;

    /**
     * 接收者的账号id
     */
    private String receiverId;

    /**
     * 消息类型
     */
    private String type;

    /**
     * 信息内容
     */
    private String content;
}