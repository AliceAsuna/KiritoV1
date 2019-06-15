package com.alice.springboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestClass {

    @Test
    public void Test()
    {

    }

    public static void main(String[] args)
    {
        SimpleDateFormat test = new SimpleDateFormat("yyyy-MM-dd");
        try {
            test.parse("2019-5-4");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
