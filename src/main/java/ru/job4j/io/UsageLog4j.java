package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Roman";
        int age = 32;
        long depth = 12345L;
        double height = 32.3;
        float width = 4.4f;
        short distance = 2;
        byte weight = 74;
        char firstLetter = 'A';
        LOG.debug("8 different variables - name : {}, age : {}, depth : {}, height : {}, width : {}, distance : {},"
                + "weight : {}, firstLetter : {}", name, age, depth, height, width, distance, weight, firstLetter);
    }
}