package top.maserhe.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * @author Maserhe
 * @description 前端传来的用户信息的封装类
 * @date 2020/1/8 23:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UsersBO {

    private String userId;
    private String faceData;
    private String nickname;

}