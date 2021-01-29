package com.example.hello.Controller;

import com.example.hello.Service.ImpI.JudgeServiceImpl;
import com.example.hello.Service.ImpI.VTServiceImpl;
import com.example.hello.Service.JudgeService;
import com.example.hello.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class VoteController {

    @Autowired
    VTServiceImpl vtService;

    @Autowired
    JudgeServiceImpl judgeService;

    @Autowired
    UserService userService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/vote")
    public String viewVote(HttpSession session, Model model){
        String judgeAccount = (String)session.getAttribute("judgeAccount");
        String username = (String)session.getAttribute("username");
        int Round = userService.getRound(username);
        model.addAttribute("round",Round);
        String name = judgeService.getName(username,judgeAccount);
        String vote = "yes";
        if(session.getAttribute("vote")!=null) {
            vote = (String) session.getAttribute("vote");
        }
        model.addAttribute("name",name);
        List<Map<String,Object>> mapList = vtService.chooseTargets(username);
        model.addAttribute("targets",mapList);
        if(judgeService.isPart(username,judgeAccount)==false) {
            model.addAttribute("msg", "没有投票权限");
        }
        else if(vote.equals("no")){
            model.addAttribute("msg", "您已弃权");
        }
        else if(stringRedisTemplate.opsForValue().get("vote").equals("no")){
            model.addAttribute("msg", "您已弃权");
        }
        else if(stringRedisTemplate.opsForValue().get("share").equals("no")){
            if(judgeService.isVote(username,judgeAccount)==true){
                model.addAttribute("msg","您已投票");
            }
        }
        else {
            int round = userService.getRound(username);

            if (session.getAttribute("round") == null) {
                session.setAttribute("round", round);
            }
            int sRound = (int) session.getAttribute("round");
            if (session.getAttribute("voted") != null) {
                if ((int) session.getAttribute("voted") == 1) {
                    if (sRound == round) {
                        model.addAttribute("msg", "您已投票");
                    }
                }
            }
        }
        return "vote";
    }

    @RequestMapping("/voteIn")
    public String voteIn(@RequestParam("name") String name,HttpSession session,Model model){
        String judgeAccount = (String)session.getAttribute("judgeAccount");
        String username = (String)session.getAttribute("username");
        if(stringRedisTemplate.opsForValue().get("share").equals("yes")) {
            int round = userService.getRound(username);
            if (session.getAttribute("round") == null) {
                session.setAttribute("round", round);
            }
            int sRound = (int) session.getAttribute("round");
            if (sRound != round) {
                session.setAttribute("voted", 0);
                session.setAttribute("round", round);
            }
            List<Map<String, Object>> mapList = vtService.chooseTargets(username);
            model.addAttribute("targets", mapList);
            if (judgeService.isPart(username, judgeAccount) == false) {
                return "redirect:/vote";
            }
            else if (session.getAttribute("voted") != null) {
                if ((int) session.getAttribute("voted") == 1) {
                    return "redirect:/vote";
                }
            }
            else {
                vtService.voteIn(username,name);
                judgeService.setVote(username,judgeAccount);
                session.setAttribute("voted",1);
                return "redirect:/vote";
            }
        }
        if(stringRedisTemplate.opsForValue().get("share").equals("no")&&judgeService.isVote(username,judgeAccount)==false){
            if(judgeService.isPart(username,judgeAccount)==true) {
                vtService.voteIn(username, name);
                judgeService.setVote(username, judgeAccount);
                return "redirect:/vote";
            }
        }
        return "redirect:/vote";
    }

    @RequestMapping("/voteChoose")
    public String voteChoose(HttpSession session, Model model){
        String username = (String)session.getAttribute("username");
        judgeService.setNotVote(username);
        userService.setRound(username);
        List<Map<String,Object>> mapList1 = vtService.getTargets(username);
        model.addAttribute("targets",mapList1);
        List<Map<String,Object>> mapList2 = judgeService.getJudges(username);
        model.addAttribute("judges",mapList2);
        if(session.getAttribute("msg")!=null) {
            if (((String) session.getAttribute("msg")).equals("yes")) {
                model.addAttribute("msg", "目标和评委不能为空");
            }
        }
        return "voteChoose";
    }

    @RequestMapping("/voteDecide")
    public String voteDecide(HttpServletRequest request, HttpServletResponse response,HttpSession session, Model model){
        String username = (String)session.getAttribute("username");
        stringRedisTemplate.opsForValue().set("vote","yes");
        vtService.setNotPart(username);
        judgeService.setNotPart(username);
        String[] target = request.getParameterValues("targetName");
        String[] judge = request.getParameterValues("judgeName");
        String share = request.getParameter("share");
        if(target==null||judge==null||share.isEmpty()){
            session.setAttribute("msg","yes");
            return "redirect:/voteChoose";
        }
        List<String> targets = Arrays.asList(target);
        List<String> judges = Arrays.asList(judge);
        stringRedisTemplate.opsForValue().set("share",share);
        vtService.setPart(username,targets);
        judgeService.setPart(username,judges);
        List<Map<String,Object>> mapList = vtService.chooseTargets(username);
        model.addAttribute("targets",mapList);;
        session.setAttribute("msg","no");
        int round = userService.getRound(username);
        model.addAttribute("round",round);
        return "view";
    }

    @RequestMapping("/noVote")
    public String noVote(HttpSession session){
        String username = (String) session.getAttribute("username");
        String judgeAccount = (String)session.getAttribute("judgeAccount");
        if(stringRedisTemplate.opsForValue().get("share").equals("no")){
            stringRedisTemplate.opsForValue().set("vote","no");
            judgeService.setVote(username, judgeAccount);
            return "redirect:/vote";
        }
        else{
            session.setAttribute("vote","no");
            session.setAttribute("voted",1);
            return "redirect:/vote";
        }
    }
}
