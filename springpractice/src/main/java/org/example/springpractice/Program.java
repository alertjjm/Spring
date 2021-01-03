package org.example.springpractice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ExamConsole console= (ExamConsole) context.getBean("console");
        console.print();
    }
}
