package com.example.hello.Service.ImpI;

import com.example.hello.Dao.Impl.UserDaoImpl;
import com.example.hello.Dao.Impl.VTDaoImpl;
import com.example.hello.Dao.VTDao;
import com.example.hello.Entity.User;
import com.example.hello.Entity.VoteTarget;
import com.example.hello.Service.VTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class VTServiceImpl implements VTService {

    @Autowired
    VTDaoImpl vTDao;

    @Autowired
    UserDaoImpl userDao;

    VoteTarget votetarget = new VoteTarget();
    @Override
    public void addTargets(String[] arr,String username) {
        List<String> list = Arrays.asList(arr);
        vTDao.addTargets(list,username);
    }

    @Override
    public List<Map<String, Object>> getTargets(String username) {
        List<Map<String, Object>> mapList = vTDao.getTargets(username);
        return mapList;
    }

    @Override
    public void voteIn(String username, String name) {
        vTDao.voteIn(username,name);
    }

    @Override
    public List<Map<String, Object>> chooseTargets(String username) {
        List<Map<String, Object>> mapList = vTDao.getTargets(username);
        for(int i = 0;i<mapList.size();i++){
            if(!(boolean)mapList.get(i).get("participate")){
                mapList.remove(i);
                i--;
            }
        }
        return mapList;
    }

    @Override
    public void setPart(String username, List<String> list) {
        vTDao.setPart(username,list);
    }

    @Override
    public void setNotPart(String username) {
        vTDao.setNotPart(username);
    }

    @Override
    public void setNoVotes(String username) {
        vTDao.setNoVotes(username);
    }
}
