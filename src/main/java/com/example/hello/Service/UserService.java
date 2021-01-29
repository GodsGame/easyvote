package com.example.hello.Service;

import com.example.hello.Entity.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

public interface UserService {
    void addUser(User user);
    boolean isNewProject(String username);
    void setNewProject(String username);
    void setNoProject(String username);
    String getUsername(Integer id);
    void changePassword(String username,String password);
    void setRound(String username);
    int getRound(String username);
    void voteRound(int round, HttpSession session);
    void delete(String username);
    int getId(String username);
    void setZRound(String username);
}
