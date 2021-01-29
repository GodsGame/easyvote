package com.example.hello.Controller;

import com.example.hello.Dao.Impl.JudgeDaoImpl;
import com.example.hello.Entity.Judge;
import com.example.hello.Service.JudgeService;
import com.example.hello.Service.VTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class JudgeController {

    @Autowired
    JudgeDaoImpl judgeDao;

    @Autowired
    JudgeService judgeService;

    @Autowired
    VTService vtService;

    @RequestMapping("/judge")
    public String judge(HttpSession session, Model model){
        String username = (String)session.getAttribute("username");
        List<Map<String,Object>> mapList = judgeService.getJudges(username);
        model.addAttribute("judges",mapList);
        return "judge";
    }

    @RequestMapping("/judge-sub")
    public String addJudges(HttpServletRequest request, HttpSession session){
        String username = (String)session.getAttribute("username");
        judgeService.delete(username);
        List<Judge> judges = new ArrayList<Judge>();
        String[] names = request.getParameterValues("name");
        String[] accounts = request.getParameterValues("account");
        String[] passwords = request.getParameterValues("password");
        for(int i=0;i<names.length;++i){
            if(names[i].isEmpty()||accounts[i].isEmpty()||passwords[i].isEmpty()) {
                continue;
            }
            else {
                Judge judge = new Judge();
                judge.setName(names[i]);
                judge.setAccount(accounts[i]);
                judge.setPassword(passwords[i]);
                judges.add(i, judge);
            }
        }
        judgeDao.addJudges(judges,username);
        return "redirect:/project";
    }

}
