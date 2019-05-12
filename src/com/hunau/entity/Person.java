package com.hunau.entity;/* *
 * @Description:
 * @param $params$
 * @Return: $returns$
 * @开发人员：余新伟
 * @开发单位：湖南农业大学物联网工程专业
 * @Date: 2019/5/11 15:30
 * @开发版本：综合练习V0.1
 */

abstract public class Person {
    private int id;
    private String name;
    private String pwd;
    private String sexy;
    private boolean isused;

    public  Person (){};

    public Person(int id,String name,String pwd){
        this.id=id;
        this.name=name;
        this.pwd=pwd;
    }

    public Person(int id,String name,String pwd,String sexy){
        this.id=id;
        this.name=name;
        this.pwd=pwd;
        this.sexy=sexy;
    }

    public Person(int id,String name,String pwd,String sexy,boolean isused){
        this.id=id;
        this.name=name;
        this.pwd=pwd;
        this.sexy=sexy;
        this.isused=isused;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSexy() {
        return sexy;
    }

    public void setSexy(String sexy) {
        this.sexy = sexy;
    }

    abstract public String show();
}
