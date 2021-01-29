package com.example.hello.Controller;

import com.example.hello.Service.IMailService;
import com.example.hello.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
public class PasswordController {

    @Autowired
    IMailService mailService;

    @Autowired
    UserService userService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/sendMail")
    public String sendMail(@RequestParam("answer") String answer,
                           @RequestParam("email") String email, HttpSession session,Model model){
        String count = stringRedisTemplate.opsForValue().get("count");
        if(!answer.equals(count)){
            model.addAttribute("msg","答案错误");
            return "password";
        }
        Random random = new Random();
        int num = random.nextInt(900000)+100000;
        String content = String.valueOf(num);
        session.setAttribute("content",content);
        mailService.sendMail(email,"验证码",content);
        return "password";
    }

    @RequestMapping("/changePassword")
    public String changePassword(@RequestParam("password") String password,
                                 @RequestParam("rePassword") String rePassword,
                                 @RequestParam("verification") String code, HttpSession session, Model model){
        String content = (String) session.getAttribute("content");
        String username = (String) session.getAttribute("username");
        if (!password.equals(rePassword)){
            model.addAttribute("msg","密码输入不一致!");
            System.out.println("错误");
            return "password";
        }
        else if (!code.equals(content)){
            model.addAttribute("msg","验证码错误!");
            return "password";
        }
        else {
            userService.changePassword(username,password);
            System.out.println("修改成功");
            return "login";
        }
    }
}
