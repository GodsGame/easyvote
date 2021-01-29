package com.example.hello.Controller;

import com.example.hello.Dao.Impl.JudgeDaoImpl;
import com.example.hello.Dao.Impl.UserDaoImpl;
import com.example.hello.Dao.UserDao;
import com.example.hello.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserDao userDao;

    @Autowired
    JudgeDaoImpl judgeDao;

    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("id") Integer id,
                        Model model, HttpSession session, HttpServletRequest request){
        if(id!=null){
            if(judgeDao.getJudgePassword(userDao.getUsername(id),username).equals(password)){
                session.setAttribute("username",userDao.getUsername(id));
                session.setAttribute("judgeAccount",username);
                return "redirect:/vote";
            }
            else{
                model.addAttribute("msg","用户名或密码或id不正确!");
                return "login";
            }
        }
        else if(userDao.exUser(username)&&(userDao.getUserPassword(username)).equals(password)){
            session.setAttribute("loginUser","login");
            session.setAttribute("username",username);
            return "redirect:/main";
        }
        else{
            model.addAttribute("msg","用户名或密码不正确!");
            return "login";
        }
    }
}
