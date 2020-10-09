package cn.zhu4wp.seckill.service;

import cn.zhu4wp.seckill.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * UserService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>09/02/2020</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Resource
    private UserService userService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: login(HttpServletResponse response, LoginVO loginVO)
     */
    @Test
    public void testLogin() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * 注册用户测试
     */
    @Test
    public void testRegister() throws Exception {
        User user = new User();
        user.setPhoneNum(13298766543l);
        user.setNickname("hello");
        user.setPassword("111111");
        user.setRegisterDate(new Date());
        userService.register(user);
    }

    /**
     * Method: getById(long id)
     */
    @Test
    public void testGetByPhoneNum() throws Exception {
//TODO: Test goes here...
        User user = userService.getByPhoneNum(13298766543l);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(user.getRegisterDate()));
    }

    /**
     * Method: updatePassword(String token, long id, String inputPass)
     */
    @Test
    public void testUpdatePassword() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: addCookie(HttpServletResponse response, String token, User user)
     */
    @Test
    public void testAddCookie() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getByToken(HttpServletResponse response, String token)
     */
    @Test
    public void testGetByToken() throws Exception {
//TODO: Test goes here... 
    }


} 
