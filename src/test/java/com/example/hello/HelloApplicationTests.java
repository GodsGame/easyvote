package com.example.hello;

import com.example.hello.Dao.Impl.VTDaoImpl;
import com.example.hello.Dao.VTDao;
import com.example.hello.Entity.VoteTarget;
import com.example.hello.Service.IMailService;
import com.example.hello.Service.ImpI.VTServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@SpringBootTest
class HelloApplicationTests {

    @Autowired
    VTDaoImpl vtDao;

    @Autowired
    VTServiceImpl vtService;

    @Autowired
    private IMailService mailService;

    @Test
    void contextLoads() throws SQLException {
        Random random = new Random();
        int num = random.nextInt(900000)+100000;
        String content = String.valueOf(num);
        mailService.sendMail("a1312352545@163.com","验证码",content);
    }

}
