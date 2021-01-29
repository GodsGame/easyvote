package com.example.hello.Controller;

import com.example.hello.Entity.User;
import com.example.hello.Service.ImpI.UserServiceImpl;
import com.example.hello.Service.ImpI.VTServiceImpl;
import com.example.hello.Service.JudgeService;
import com.example.hello.Service.VTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;

@Controller
public class NewIndexController {

    @Autowired
    VTServiceImpl vtService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    JudgeService judgeService;

    User user = new User();
    @RequestMapping("/index")
    public String addTargets(HttpServletRequest request, HttpSession session, Model model){
        String username = (String)session.getAttribute("username");
        if(userService.isNewProject(username)){
            model.addAttribute("msg","有项目未完成，无法创建");
            return "index";
        }
        else {
            String[] targets = request.getParameterValues("target");

            vtService.addTargets(targets,username);
            userService.setNewProject(username);
            judgeService.creat(username);
            return "redirect:/project";
        }
    }
    
}
