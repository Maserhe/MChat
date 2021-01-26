package top.maserhe.pojo;

public class Friends {
    private String id;

    private String myUserId;

    private String myFriendUserId;

    public Friends(String id, String myUserId, String myFriendUserId) {
        this.id = id;
        this.myUserId = myUserId;
        this.myFriendUserId = myFriendUserId;
    }

    public Friends() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMyUserId() {
        return myUserId;
    }

    public void setMyUserId(String myUserId) {
        this.myUserId = myUserId == null ? null : myUserId.trim();
    }

    public String getMyFriendUserId() {
        return myFriendUserId;
    }

    public void setMyFriendUserId(String myFriendUserId) {
        this.myFriendUserId = myFriendUserId == null ? null : myFriendUserId.trim();
    }
}