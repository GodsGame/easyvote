package com.example.hello.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
public class User {

    private String username;
    private String password;
    private boolean ExistProject;

    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isExistProject() {
        return ExistProject;
    }

    public void setExistProject(boolean existProject) {
        this.ExistProject = existProject;
    }
}
