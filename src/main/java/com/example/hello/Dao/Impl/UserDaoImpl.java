package com.example.hello.Dao.Impl;

import com.example.hello.Dao.BaseDao;
import com.example.hello.Dao.UserDao;
import com.example.hello.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    BaseDao baseDao;


    @Override
    public boolean exUser(String username) {
        Connection conn = BaseDao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("select * from user where username = ?");
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();
            if(!resultSet.next())
                return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    @Override
    public String getUserPassword(String username) {
        String password = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("select * from user where username = ?");
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            password = resultSet.getString("password");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return password;
    }

    @Override
    public boolean IsNewProject(String username) {
        boolean isNew = false;
        Connection conn = BaseDao.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from user where username = ?");
            stmt.setString(1,username);
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            isNew = resultSet.getBoolean("project");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isNew;
    }

    @Override
    public void setNewProject(String username) {
        Connection conn = BaseDao.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("update user set project = 1 where username = ?");
            stmt.setString(1,username);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void setNoProject(String username) {
        Connection conn = baseDao.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("update user set project = 0 where username = ?");
            stmt.setString(1,username);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public String getUsername(Integer id) {
        Connection connection = baseDao.getConnection();
        String username = null;
        try {
            PreparedStatement statement = connection.prepareStatement("select * from user where id = ?");
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            username = resultSet.getString("username");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return username;
    }

    @Override
    public void changePassword(String username,String password) {
        Connection conn = baseDao.getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement("update user set password = ? where username = ?");
            statement.setString(1,password);
            statement.setString(2,username);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addUser(User user) {
        Connection conn = BaseDao.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("insert into user(username,password,project,round) values (?,?,?,?)");
            stmt.setString(1,user.getUsername());
            stmt.setString(2,user.getPassword());
            stmt.setInt(3,0);
            stmt.setInt(4,0);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void setRound(String username) {
        int round = 0;
        Connection connection = baseDao.getConnection();
        try {
            PreparedStatement statement1 = connection.prepareStatement("select * from user where username = ?");
            statement1.setString(1,username);
            ResultSet resultSet = statement1.executeQuery();
            resultSet.next();
            round = resultSet.getInt("round")+1;
            PreparedStatement statement2 = connection.prepareStatement("update user set round ="+round+" where username = ?");
            statement2.setString(1,username);
            statement2.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public int getRound(String username) {
        Connection connection = baseDao.getConnection();
        String sql = "select round from user where username = ?";
        int round = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            round = resultSet.getInt("round");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return round;
    }

    @Override
    public void serZRound(String username) {
        Connection connection = baseDao.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update user set round = 0 where username = ?");
            statement.setString(1,username);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(String username) {
       Connection connection = baseDao.getConnection();
       String sql1 = "drop table if exists "+username+"_target";
       String sql2 = "drop table if exists "+username+"_judge";
        try {
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.executeUpdate();
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement2.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public int getId(String username) {
        Connection connection = baseDao.getConnection();
        String sql = "select id from user where username = ?";
        int id = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            id = resultSet.getInt("id");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }
}
