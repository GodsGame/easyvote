package com.example.hello.Service;

import com.example.hello.Entity.VoteTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface VTService {
    void addTargets(String[] arr,String username);
    List<Map<String,Object>> getTargets(String username);
    void voteIn(String username,String name);
    List<Map<String,Object>> chooseTargets(String username);
    void setPart(String username,List<String> list);
    void setNotPart(String username);
    void setNoVotes(String username);
}
