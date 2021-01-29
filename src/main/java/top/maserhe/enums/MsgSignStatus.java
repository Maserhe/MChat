package top.maserhe.enums;

/**
 * @author Maserhe
 * @description
 * @date 2020/1/12 16:33
 */
public enum MsgSignStatus {

    /**
     * 消息签收的状态
     */
    UNSIGN(0, "未签收"),
    SIGNED(1, "已签收");

    public final Integer status;
    public final String content;

    MsgSignStatus(Integer status, String content){
        this.status = status;
        this.content = content;
    }
}