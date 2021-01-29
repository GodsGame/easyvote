package com.example.hello.Dao.Impl;

import com.example.hello.Dao.BaseDao;
import com.example.hello.Dao.VTDao;
import com.example.hello.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class VTDaoImpl implements VTDao {

    @Autowired
    BaseDao baseDao;

    @Autowired
    UserDaoImpl userDao;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void addTargets(List<String> list,String username){
        Connection conn = baseDao.getConnection();
        try {
            PreparedStatement stmt1 = conn.prepareStatement("create table "+username+"_target(Id int primary key auto_increment,name varchar(255),votes int(64),participate tinyint(1))");
            stmt1.executeUpdate();
            for(String i : list){
                if(StringUtils.isEmpty(i))
                    continue;
                PreparedStatement stmt2 = conn.prepareStatement("insert into "+username+"_target(name,votes,participate) values(?,?,?)");
                stmt2.setString(1,i);
                stmt2.setInt(2,0);
                stmt2.setInt(3,0);
                stmt2.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Map<String,Object>> getTargets(String username) {
        List<Map<String,Object>> mapList = jdbcTemplate.queryForList("select * from "+username+"_target");
        return mapList;
    }

    @Override
    public void voteIn(String username, String name) {
        int votes = 0;
        Connection connection = baseDao.getConnection();
        try {
            PreparedStatement statement1 = connection.prepareStatement("select * from "+username+"_target where name = ?");
            statement1.setString(1,name);
            ResultSet resultSet = statement1.executeQuery();
            resultSet.next();
            votes = resultSet.getInt("votes")+1;
            PreparedStatement statement2 = connection.prepareStatement("update "+username+"_target set votes ="+votes+" where name = ?");
            statement2.setString(1,name);
            statement2.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void setPart(String username, List<String> list) {
        Connection connection = baseDao.getConnection();
        try {
            for(String name : list) {
                String sql = "update "+username+"_target set participate = 1 where name = ?";
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
            String sql = "update "+username+"_target set participate = 0";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void setNoVotes(String username) {
        Connection connection = baseDao.getConnection();
        try {
            String sql = "update "+username+"_target set votes = 0";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
