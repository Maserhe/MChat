package top.maserhe.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Maserhe
 * @description
 * @date 2020/1/16 15:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendsVO {

    /**
     * 好友的账号id
     */
    private String friendId;

    /**
     * 处理的类型
     */
    private String friendUsername;

    /**
     * 我给好友的备注
     */
    private String friendFaceImage;

    /**
     * 好友给我的备注
     */
    private String friendNickname;
}