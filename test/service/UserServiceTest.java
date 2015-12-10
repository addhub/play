package service;

import model.User;
import org.bson.Document;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
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
        testUser=new User("unitTest","testInit");
        testUser.setDisplayName("TEST");

        testCreateUser();

    }


    public static void testCreateUser() {
        Document user = userService.createUser(testUser);
        assertNotNull(user.getString("username"));
    }

    @Ignore
    @Test
    public void testUpdateUser() throws Exception {
        User user=userService.getUser("unitTest");
        user.setDisplayName("test");
        long l = userService.updateUser(user);
        assertTrue(l>0);
        User test = userService.getUser("unitTest");
        assertEquals("test", test.getDisplayName());

    }

    @Test
    public void testGetUser() throws Exception {
        User test = userService.getUser("unitTest");
        assertNotNull(test);
    }
}