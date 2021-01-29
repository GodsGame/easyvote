package com.example.hello.Dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface VTDao {
    void addTargets(List<String> list,String username);
    List<Map<String,Object>> getTargets(String username);
    void voteIn(String username,String name);
    void setPart(String username,List<String> list);
    void setNotPart(String username);
    void setNoVotes(String username);
}
