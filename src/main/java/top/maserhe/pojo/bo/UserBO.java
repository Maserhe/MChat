package top.maserhe.pojo.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author monkJay
 * @description 前端传来的用户信息的封装类
 * @date 2020/1/8 23:59
 */
@Data
@NoArgsConstructor
public class UserBO {
    private String userId;
    private String faceData;
    private String nickname;
}