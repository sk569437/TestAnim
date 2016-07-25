package com.jjws.model;

/**
 * Created by sk on 16-7-18.
 */
public class Person {


    private String id;
    private String name;
    private String sign;
    private String sex;



    public Person() {
    }

    public Person(String id, String name, String sign, String sex) {
        this.id = id;
        this.name = name;
        this.sign = sign;
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
