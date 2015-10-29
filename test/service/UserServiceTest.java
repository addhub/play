package service;

import model.User;
import org.bson.Document;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sasinda on 10/28/15.
 */
public class UserServiceTest {

    static UserService userService;
    static User testUser;

    @BeforeClass
    public static void setup(){
        userService=new UserService();
        testUser=new User("test","testInit");
        testUser.setDisplayName("TEST");

    }

    @Test
    public void testCreateUser() throws Exception {
        Document user = userService.createUser(testUser);
        assertNotNull(user.getString("username"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user=userService.getUser("test");
        user.setPassword("test");
        long l = userService.updateUser(user);
        assertTrue(l>0);
        User test = userService.getUser("test");
        assertEquals("test", test.getPassword());

    }

    @Test
    public void testGetUser() throws Exception {
        User test = userService.getUser("test");
        assertNotNull(test);
    }
}