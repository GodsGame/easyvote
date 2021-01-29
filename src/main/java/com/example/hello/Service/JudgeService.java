package com.example.hello.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface JudgeService {
    void creat(String username);
    List<Map<String,Object>> getJudges(String username);
    void delete(String username);
    void setPart(String username,List<String> list);
    void setNotPart(String username);
    boolean isPart(String username,String account);
    void setVote(String username,String account);
    void setNotVote(String username);
    boolean isVote(String username,String account);
    String getName(String username,String account);
}
