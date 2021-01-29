package com.example.hello.Dao;

import com.example.hello.Entity.Judge;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface JudgeDao {
    void creat(String username);
    void addJudges(List<Judge> mapList, String username);
    void delete(String username);
    List<Map<String,Object>> getJudges(String username);
    String getJudgePassword(String username,String account);
    void setPart(String username,List<String> list);
    void setNotPart(String username);
    boolean isPart(String username,String account);
    void setVote(String username,String account);
    void setNotVote(String username);
    boolean isVote(String username,String account);
    String getName(String username,String account);
}
