package com.example.hello.Dao;

import com.example.hello.Entity.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

@Repository
public interface UserDao {
    void addUser(User user);
    boolean exUser(String username);
    String getUserPassword(String username);
    boolean IsNewProject(String username);
    void setNewProject(String username);
    void setNoProject(String username);
    String getUsername(Integer id);
    void changePassword(String username,String password);
    void setRound(String username);
    int getRound(String username);
    void delete(String username);
    int getId(String username);
    void serZRound(String username);
}

