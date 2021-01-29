package com.example.hello.Entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class Judge {
    private String name;
    private String account;
    private String password;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
