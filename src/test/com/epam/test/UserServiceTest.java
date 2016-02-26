package com.epam.test;

import com.epam.dao.impl.UserDaoImpl;
import com.epam.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Olga Bogutska on 26.02.2016.
 */
@ContextConfiguration(locations = {"classpath:spring.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
    @Autowired
    private UserDaoImpl userDao;

    @Test
    public void testContext() {
        assertNotNull(userDao);
    }

    @Test
    public void testGetByName() {
        String name = "Olga";
        User user = userDao.getByName(name);
        assertNotNull(userDao);
        assertEquals(user.getName(), name);
    }
}
