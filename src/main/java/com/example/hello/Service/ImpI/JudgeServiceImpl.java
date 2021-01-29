package com.example.hello.Service.ImpI;

import com.example.hello.Dao.Impl.JudgeDaoImpl;
import com.example.hello.Service.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JudgeServiceImpl  implements JudgeService {

    @Autowired
    JudgeDaoImpl judgeDao;

    @Override
    public void creat(String username) {
        judgeDao.creat(username);
    }

    @Override
    public void delete(String username) {
        judgeDao.delete(username);
    }

    @Override
    public List<Map<String, Object>> getJudges(String username) {
        List<Map<String, Object>> mapList = judgeDao.getJudges(username);
        return mapList;
    }

    @Override
    public void setPart(String username, List<String> list) {
        judgeDao.setPart(username,list);
    }

    @Override
    public boolean isPart(String username, String account) {
        boolean isPart = judgeDao.isPart(username,account);
        return isPart;
    }

    @Override
    public void setNotPart(String username) {
        judgeDao.setNotPart(username);
    }

    @Override
    public void setVote(String username, String account) {
        judgeDao.setVote(username,account);
    }

    @Override
    public void setNotVote(String username) {
        judgeDao.setNotVote(username);
    }

    @Override
    public boolean isVote(String username, String account) {
        boolean isVote = judgeDao.isVote(username,account);
        return isVote;
    }

    @Override
    public String getName(String username, String account) {
        String name = judgeDao.getName(username,account);
        return name;
    }
}
