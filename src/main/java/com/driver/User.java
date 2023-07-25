package com.driver;

import java.util.List;

public class User {
    private String name;
    private String mobile;

    public User() {}

    public User(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }
    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o!=null && this.getClass()!=o.getClass()){
            return false;
        }
        User user = (User) o;
        if(this.getMobile().equals(user.getMobile())){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        int result = getMobile().hashCode();
        return result;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
