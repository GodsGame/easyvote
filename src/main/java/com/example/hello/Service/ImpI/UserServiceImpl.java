package com.example.hello.Service.ImpI;

import com.example.hello.Dao.Impl.UserDaoImpl;
import com.example.hello.Dao.UserDao;
import com.example.hello.Entity.User;
import com.example.hello.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDaoImpl userDao;

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public boolean isNewProject(String username) {
        boolean isNew = userDao.IsNewProject(username);
        return isNew;
    }

    @Override
    public void setNewProject(String username) {
        userDao.setNewProject(username);
    }

    @Override
    public String getUsername(Integer id) {
        String username = userDao.getUsername(id);
        return username;
    }

    @Override
    public void changePassword(String username, String password) {
        userDao.changePassword(username,password);
    }

    @Override
    public void setRound(String username) {
        userDao.setRound(username);
    }

    @Override
    public int getRound(String username) {
        int round = userDao.getRound(username);
        return round;
    }

    @Override
    public void voteRound(int round, HttpSession session) {

    }

    @Override
    public void delete(String username) {
        userDao.delete(username);
    }

    @Override
    public void setNoProject(String username) {
        userDao.setNoProject(username);
    }

    @Override
    public int getId(String username) {
        int id = userDao.getId(username);
        return id;
    }

    @Override
    public void setZRound(String username) {
        userDao.serZRound(username);
    }
}
