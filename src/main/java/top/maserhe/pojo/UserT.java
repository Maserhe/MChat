package top.maserhe.pojo;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-26 22:51
 */
public class UserT {

    private String username;
    private String password;

    public UserT(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserT{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
