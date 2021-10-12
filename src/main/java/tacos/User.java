package tacos;

import com.fasterxml.jackson.annotation.JsonView;

import javax.validation.constraints.NotBlank;

public class User {

    /**
     *创建两个接口，一个为用户简单视图，即精简版的Json数据视图，另一个为用户详细视图，即完整版的Json数据视图
     */
    public interface UserSimpleView{};
    public interface UserDetailView extends UserSimpleView{};

    @NotBlank
    private String username;
    private String password;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // 在对象的GET方法上指定视图
    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(UserSimpleView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
