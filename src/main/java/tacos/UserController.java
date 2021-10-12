package tacos;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.core.style.ToStringStyler;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @RequestMapping(value="/user",method = RequestMethod.GET)
    public List<User> query(){
        List<User> userList=new ArrayList();
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        return userList;
    }

    @RequestMapping(value="/user2",method = RequestMethod.GET)
    public List<User> query2(@RequestParam String username){

        // 打印观察是否收到username参数
        System.out.println("username is :"+username);
        List<User> userList=new ArrayList();
        // 将list中添加了三个空的user对象，这里暂时没给user进行属性赋值
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        return userList;
    }

    @RequestMapping(value="/user3",method = RequestMethod.GET)
    public List<User> query3(@RequestParam(name="username") String myname){

        // 打印观察是否收到username参数
        System.out.println("username is :"+myname);
        List<User> userList=new ArrayList();
        // 将list中添加了三个空的user对象，这里暂时没给user进行属性赋值
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        return userList;
    }

    /**
     * @测试多参数传递
     * @return
     */
    @RequestMapping(value="/user4",method = RequestMethod.GET)
    public List<User> query4(UserQuery userQuery){

        // 通过反射的方式进行打印效果
        System.out.println(ReflectionToStringBuilder.toString(userQuery, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println("username:"+userQuery.getUsername());
        System.out.println("username:"+userQuery.getAge());
        System.out.println("username:"+userQuery.getToAge());
        List<User> userList=new ArrayList();
        // 将list中添加了三个空的user对象，这里暂时没给user进行属性赋值
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        return userList;
    }

    /**
     * @测试多参数传递
     * @return
     */
    @RequestMapping(value="/user5",method = RequestMethod.GET)
    public List<User> query5(UserQuery userQuery, @PageableDefault Pageable pageable){

        // 通过反射的方式进行打印效果
        System.out.println(ReflectionToStringBuilder.toString(userQuery, ToStringStyle.MULTI_LINE_STYLE));

        System.out.println(pageable.getNumberOfPages());

        List<User> userList=new ArrayList();
        // 将list中添加了三个空的user对象，这里暂时没给user进行属性赋值
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        return userList;
    }

    /**
     * @测试多参数传递
     * @return
     */
    @RequestMapping(value="/user/{id}",method = RequestMethod.GET)
    public User getInfo(@PathVariable(value="id") String idid){
        System.out.println("id is:"+idid);
        User user=new User();
        user.setUsername("tom");
        return user;
    }

    /**
     * @Description 查询用户详情，测试JsonView的功能
     */
    @RequestMapping(value="/user/{id:\\d+}")
    @JsonView(User.UserSimpleView.class)
    public User getInfo2(@PathVariable(value="id") String id){
        System.out.println("id is :"+id);
        User user=new User();
        user.setUsername("tom");
        user.setPassword("123456");
        return user;
    }

    /**
     *                          以上是 HTTP GET 方法的测试控制方法
     */

    @PostMapping("/user")
    public User createInfo(@RequestBody User user){
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
//        user.setUsername("Bob");
        return user;
    }

    @PostMapping("/user2")
    public User createInfo2(@Valid  @RequestBody User user){
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        user.setUsername("Bob");
        return user;
    }

    @PostMapping("/user3")
    public User createInfo3(@Valid  @RequestBody User user, BindingResult errors){
        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> System.out.println("error message: "+error.getDefaultMessage()));
        }
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        user.setUsername("Bob");
        return user;
    }

    /**
     * @Description 测试使用HTTP PUT方法进行更新用户信息。
     */
    @PutMapping("/user/{id}")
    public User putInfo(@Valid  @RequestBody User user, BindingResult errors){
        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> System.out.println("error message: "+error.getDefaultMessage()));
        }
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
//        user.setUsername("Bob");
        return user;
    }

    /**
     * @Description 测试使用HTTP DELETE方法进行删除用户信息。
     */
    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id){
        System.out.println("id="+id);
    }

}
