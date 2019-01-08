package com.springboot.datasource;

import com.springboot.datasource.service.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootDatasourceApplicationTests {

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void contextLoads() {
        try {
            userInfoService.insertC();
        }catch (Exception e){

        }

    }

}
