package org.example.TobySpring;
import org.example.TobySpring.Dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

public class UserDaoTest {
    @Test
    public void andAndGet() throws SQLException, ClassNotFoundException {
        ApplicationContext context=new GenericXmlApplicationContext("applicationContext.xml");
        UserDao userDao=context.getBean("userDao", UserDao.class);
        User user= new User();
        user.setName("jjm");
        user.setPassword("123");
        user.setId("alertjjm");
        userDao.add(user);
        User user1=userDao.get(user.getId());
        assertThat(user1.getName(),is(user.getName()));
        assertThat(user1.getPassword(),is(user.getPassword()));
        userDao.deleteAll();
        assertThat(userDao.getCount(),is(0));
    }
}
