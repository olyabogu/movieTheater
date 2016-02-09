package com.epam;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by Olga Bogutska on 08.02.2016.
 */
public class Launcher {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        MovieManager movieManager = context.getBean(MovieManager.class);
        movieManager.startManager(System.in, System.out);
    }
}
