package com.example.hello.Dao.Impl;

import com.example.hello.Dao.BaseDao;
import com.example.hello.Dao.JudgeDao;
import com.example.hello.Entity.Judge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class JudgeDaoImpl implements JudgeDao {

    @Autowired
    BaseDao baseDao;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void creat(String username) {
        Connection connection = baseDao.getConnection();
        try {
            String sql = "create table "+username+"_judge(name varchar(255),account varchar(255),password varchar(255),voted tinyint(1),participate tinyint(1))";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addJudges(List<Judge> judges, String username) {
        Connection connection = baseDao.getConnection();
        try {
            for(Judge judge : judges){
                PreparedStatement statement = connection.prepareStatement("insert into "+username+"_judge(name,account,password,voted,participate) values(?,?,?,?,?)");
                statement.setString(1, judge.getName());
                statement.setString(2, judge.getAccount());
                statement.setString(3, judge.getPassword());
                statement.setInt(4,0);
                statement.setInt(5,0);
                statement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(String username) {
        Connection connection = baseDao.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("delete from "+username+"_judge");
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public String getJudgePassword(String username, String account) {
        Connection connection = baseDao.getConnection();
        String password = null;
        try {
            PreparedStatement statement = connection.prepareStatement("select password from "+username+"_judge where account = ?");
            statement.setString(1,account);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            password = resultSet.getString("password");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return password;
    }

    @Override
    public List<Map<String, Object>> getJudges(String username) {
        List<Map<String,Object>> mapList = jdbcTemplate.queryForList("select * from "+username+"_judge");
        return mapList;
    }

    @Override
    public void setPart(String username, List<String> list) {
        Connection connection = baseDao.getConnection();
        try {
            for(String name : list) {
                String sql = "update "+username+"_judge set participate = 1 where name = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1,name);
                statement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void setNotPart(String username) {
        Connection connection = baseDao.getConnection();
        try {
            String sql = "update "+username+"_judge set participate = 0";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean isPart(String username, String account) {
        Connection connection = baseDao.getConnection();
        boolean isPart = false;
        try {
            PreparedStatement statement = connection.prepareStatement("select participate from "+username+"_judge where account = ?");
            statement.setString(1,account);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            isPart = resultSet.getBoolean("participate");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isPart;
    }

    @Override
    public void setVote(String username, String account) {
        Connection connection = baseDao.getConnection();
        try {
            String sql = "update "+username+"_judge set voted = 1 where account = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,account);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void setNotVote(String username) {
        Connection connection = baseDao.getConnection();
        try {
            String sql = "update "+username+"_judge set voted = 0";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean isVote(String username, String account) {
        Connection connection = baseDao.getConnection();
        boolean isVote = false;
        try {
            PreparedStatement statement = connection.prepareStatement("select voted from "+username+"_judge where account = ?");
            statement.setString(1,account);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            isVote = resultSet.getBoolean("voted");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isVote;
    }

    @Override
    public String getName(String username, String account) {
        Connection connection = baseDao.getConnection();
        String name = null;
        try {
            PreparedStatement statement = connection.prepareStatement("select name from "+username+"_judge where account = ?");
            statement.setString(1,account);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            name = resultSet.getString("name");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return name;
    }
}
