package com.example.hello.Controller;


import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RequestMapping("/app/system")
@Controller
public class SysLoginController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private Producer producer;


    @RequestMapping("number.jpg")
    public void number(HttpServletResponse response, HttpSession session, Model model) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        String text = producer.createText();
        String s1 = text.substring(0, 1);
        String s2 = text.substring(1, 2);
        int count = Integer.valueOf(s1).intValue() + Integer.valueOf(s2).intValue();
        session.setAttribute("count",count);
        model.addAttribute("count",count);
        BufferedImage image = producer.createImage(s1 + "+" + s2 + "=?");
        stringRedisTemplate.opsForValue().set("count",String.valueOf(count));
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);

    }

}
