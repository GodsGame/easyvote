package com.example.hello.Controller;

import com.example.hello.Dao.Impl.UserDaoImpl;
import com.example.hello.Entity.User;
import com.example.hello.Service.ImpI.UserServiceImpl;
import com.example.hello.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

@Controller
public class RegisteredController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/registered")
    public String registered(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("rePassword") String rePassword,Model model) {

        if(StringUtils.isEmpty(username)){
            model.addAttribute("msg","用户名不能为空!");
            return "registered";
        }
        else if(!password.equals(rePassword)){
            model.addAttribute("msg","两次密码不一致!");
            return "registered";
        }
        else if (StringUtils.isEmpty(password)){
            model.addAttribute("msg","密码不能为空!");
            return "registered";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userService.addUser(user);

        return "redirect:";
    }

}
