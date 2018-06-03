package com.example.mahe.studentmangementsystem;

public class PrincipleLogin {
    //id,username,password
    //variables
    int id;
    String username;
    String password;

    // Constructor with two parameters name and password
    //public PrincipleLogin(String name,String password)
    //{
       // this.username=name;
        //this.password=password;
    //}
    //Parameter constructor containing all three parameters
    public PrincipleLogin(int id,String name,String psd)
    {
        this.id=id;
        this.username=name;
        this.password=psd;
    }
    //getting id
    public int getId() {
        return id;
    }
    //setting id
    public void setId(int id) {
        this.id = id;
    }
    //getting name
    public String getUserName() {
        return username;
    }
    //setting name
    public void setName(String name) {
        this.username = name;
    }
    //getting password
    public String getPassword() {
        return password;
    }
    //setting password
    public void setPassword(String password) {
        this.password = password;
    }
}
