package com.example.hello.Controller;

import com.example.hello.Service.ImpI.VTServiceImpl;
import com.example.hello.Service.UserService;
import com.example.hello.Service.VTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class ViewIndexController {

    @Autowired
    VTService vtService;
    @Autowired
    UserService userService;

    @RequestMapping("/project")
    public String getTargets(Model model, HttpSession session){
        String username = (String)session.getAttribute("username");
        if(!userService.isNewProject(username)){
            model.addAttribute("msg","你还没有创建项目");
            return "index";
        }
        List<Map<String,Object>> mapList1 = vtService.getTargets(username);
        model.addAttribute("targets",mapList1);
        List<Map<String,Object>> mapList2 = vtService.getTargets(username);
        model.addAttribute("judges",mapList2);
        int id = userService.getId(username);
        model.addAttribute("id",id);
        userService.setZRound(username);
        return "viewIndex";
    }

    @RequestMapping("/delete")
    public String deleteProject(HttpSession session){
        String username = (String)session.getAttribute("username");
        userService.delete(username);
        userService.setNoProject(username);
        return "index";
    }

    @RequestMapping("/empty")
    public String empty(HttpSession session){
        String username = (String)session.getAttribute("username");
        vtService.setNoVotes(username);
        return "redirect:/project";
    }
}
