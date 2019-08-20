package com.alice.springboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.Document;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestClass {
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void Test()
    {
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();

        //移出默认编码StringHttpMessageConverter
        converterList.remove(1);
        HttpMessageConverter<?> converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        //将新的编码格式放入原来的位置，注意convert顺序错误会导致失败
        converterList.add(1, converter);

        restTemplate.setMessageConverters(converterList);
    }

    public static void main(String[] args)
    {
        SimpleDateFormat test = new SimpleDateFormat("yyyy-MM-dd");
        try
        {

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
