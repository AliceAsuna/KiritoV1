package com.alice.springboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
