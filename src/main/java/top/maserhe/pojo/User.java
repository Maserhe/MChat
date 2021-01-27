package top.maserhe.pojo;

public class User {
    private String id;

    private String username;

    private String password;

    private String faceImage;

    private String faceImageBig;

    private String nickname;

    private String qrcode;

    private String cid;

    public User(String id, String username, String password, String faceImage, String faceImageBig, String nickname, String qrcode, String cid) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.faceImage = faceImage;
        this.faceImageBig = faceImageBig;
        this.nickname = nickname;
        this.qrcode = qrcode;
        this.cid = cid;
    }

    public User() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage == null ? null : faceImage.trim();
    }

    public String getFaceImageBig() {
        return faceImageBig;
    }

    public void setFaceImageBig(String faceImageBig) {
        this.faceImageBig = faceImageBig == null ? null : faceImageBig.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode == null ? null : qrcode.trim();
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", faceImage='" + faceImage + '\'' +
                ", faceImageBig='" + faceImageBig + '\'' +
                ", nickname='" + nickname + '\'' +
                ", qrcode='" + qrcode + '\'' +
                ", cid='" + cid + '\'' +
                '}';
    }
}