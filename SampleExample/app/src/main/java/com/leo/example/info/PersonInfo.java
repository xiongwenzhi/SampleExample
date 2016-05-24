package com.leo.example.info;

import java.io.Serializable;

/**
 * Created by leo on 16/5/24.
 */
public class PersonInfo implements Serializable {
    private String name;
    private String phone;

    public PersonInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "PersonInfo{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
