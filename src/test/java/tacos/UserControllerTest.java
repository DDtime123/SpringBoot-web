package tacos;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    // 伪造测试用例，不需要执行Tomcat，运行速度会快一些
    @Autowired
    private WebApplicationContext wac;

    // 伪造一个MVC的环境
    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc= MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * @throws Exception
     * @Description 测试用例
     */
    @Test
    public void whenQuerySuccess() throws Exception{

        // 发送GET请求
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(4));
    }

    /**
     * @throws Exception
     * @Description 主要用于测试@RequestParam的单个参数接受
     */
    @Test
    public void whenQuerySuccess2() throws Exception{

        // 发送请求
        mockMvc.perform(MockMvcRequestBuilders.get("/user2")
                .param("username","tom")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * @throws Exception
     * @Description 主要用于测试@RequestParam的参数别名
     */
    @Test
    public void whenQuerySuccess3() throws Exception{

        // 发送请求
        mockMvc.perform(MockMvcRequestBuilders.get("/user3")
                .param("username","tom")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * @throws Exception
     * @Description 主要用于测试@RequestParam的多参数传递
     */
    @Test
    public void whenQuerySuccess4() throws Exception{

        // 发送请求
        mockMvc.perform(MockMvcRequestBuilders.get("/user4")
                .param("username","tom")
                .param("age","18")
                .param("toAge","20")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * @throws Exception
     * @Description 主要用于测试@RequestParam的多参数传递
     */
    @Test
    public void whenQuerySuccess5() throws Exception{

        // 发送请求
        mockMvc.perform(MockMvcRequestBuilders.get("/user5")
                .param("username","tom")
                // 分页参数，查询第三页，每页15条数据，按照age降序
                .param("page","3")
                .param("size","15")
                .param("sort","age,desc")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     *                          以上是 HTTP GET 方法的测试方法
     */

    /**
     * @throws Exception
     */
    @Test
    public void whenGetInfoSuccess() throws Exception{

        // 发送请求
        mockMvc.perform(MockMvcRequestBuilders.post("/user/2")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("tom"));
    }

    /**
     * @throws Exception
     */
    @Test
    public void whenGetInfoSuccess2() throws Exception{

        // 发送请求
        mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("tom"));
    }


    /**
     *                          以上是 HTTP GET 方法的测试方法
     */


    /**
     * @Description 使用RequestBody将J请求体中的JSON字符串映射到Controller中的Bean上
     */

    @Test
    public void whenCreateSuccess() throws Exception{
        String content="{\"username\":\"tom\",\"password\":\"123\",\"id\":\"2\"}";
        String result=mockMvc.perform(MockMvcRequestBuilders.post("/user")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println("result: "+result);
    }

    /**
     * @Description @Valid验证测试
     */

    @Test
    public void whenCreateSuccess2() throws Exception{
        String content="{\"username\":null,\"password\":\"123\"}";
        String result=mockMvc.perform(MockMvcRequestBuilders.post("/user2")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println("result: "+result);
    }

    /**
     * @Desctiption BindingResult验证
     */

    @Test
    public void whenCreateSuccess3() throws Exception{
        String content="{\"username\":null,\"password\":\"123\"}";
        String result=mockMvc.perform(MockMvcRequestBuilders.post("/user3")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println("result: "+result);
    }

    /**
     * @Description PUT方法测试
     */

    @Test
    public void whenPutSuccess() throws Exception{
        // JDK1.8的特性
        Date date=new Date(LocalDateTime.now().plusYears(1).
                atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        System.out.println(date.getTime());
        String content="{\"id\":\"1\",\"username\":\"tom\",\"password\":null,\"birthday\":"+date.getTime()+"}";
        String result=mockMvc.perform(MockMvcRequestBuilders.put("/user/4")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println("result: "+result);
    }

    /**
     * 删除程序
     * @Description DELETE方法测试
     * @throws Exceprion
     */
    @Test
    public void whenDeleteSuccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
















