package org.example.TobySpring;

import org.junit.runner.JUnitCore;

import java.sql.SQLException;

public class TestMain {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        JUnitCore.main("org.example.TobySpring.UserDaoTest");
    }
}
