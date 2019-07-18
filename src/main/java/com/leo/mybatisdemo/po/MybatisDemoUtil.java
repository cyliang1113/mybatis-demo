package com.leo.mybatisdemo.po;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MybatisDemoUtil {

    public static Properties loadProperties(String fileClasspath) {
        Properties properties = new Properties();
        InputStream resourceAsStream = Properties.class.getResourceAsStream(fileClasspath);
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}
