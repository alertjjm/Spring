package org.example.TobySpring.Dao;

import org.example.TobySpring.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

public class engine {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ApplicationContext context=new GenericXmlApplicationContext("applicationContext.xml");
        UserDao userDao=context.getBean("userDao", UserDao.class);
        User user= new User();
        User user1;
        user.setName("jjm");
        user.setPassword("123");
        user.setId("alertjjm");
        userDao.add(user);
        System.out.println("add Success!");
        user1=userDao.get("alertjjm");
        System.out.printf("[FOUND] name: %s password: %s id: %s\n",user1.getName(),user1.getPassword(),user1.getId());
        int cnt=userDao.getCount();
        System.out.printf("[COUNT] %d found\n",cnt);
    }
}
