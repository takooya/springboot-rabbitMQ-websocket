package com.example.demo.model;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String name;
    private int age;

    public User(int id, String name, int age) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User:id=" + id + ",name=" + name + ",age=" + age;
    }


}
