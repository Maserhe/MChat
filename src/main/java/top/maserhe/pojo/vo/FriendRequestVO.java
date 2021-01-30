package top.maserhe.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Maserhe
 * @description 好友请求发送方的信息
 * @date 2021/1/10 21:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendRequestVO {
    /**
     * 发送者账号ID
     */
    private String sendUserId;
    /**
     *  发送者名称。
    */
    private String username;
    /**
     * 发送者昵称
     */
    private String sendNickname;
    /**
     * 发送者头像
     */
    private String sendFaceImage;

}