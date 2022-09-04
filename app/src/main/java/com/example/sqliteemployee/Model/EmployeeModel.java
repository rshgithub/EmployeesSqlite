package com.example.sqliteemployee.Model;

public class EmployeeModel {
    private String name, email, password;
    private int id;

    // with id to get / select Post
    public EmployeeModel( String name, String email, String password, int id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.id = id;
    }

    public EmployeeModel(String name) {
        this.name = name;
    }

    // without id to insert new post
    public EmployeeModel(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }


    public String getname() {
        return name;
    }

    public void setname(String name) {
        name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}