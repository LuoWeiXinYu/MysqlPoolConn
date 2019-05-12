package com.hunau.entity;/* *
 * @Description:
 * @param $params$
 * @Return: $returns$
 * @开发人员：余新伟
 * @开发单位：湖南农业大学物联网工程专业
 * @Date: 2019/5/11 15:40
 * @开发版本：综合练习V0.1
 */

public class User extends Person {
    private boolean isused;

    public User(){

    }

    public User(int id,String name,String pwd,String sexy){
        super(id,name,pwd,sexy);
    }

    public User(int id,String name,String pwd,String sexy,boolean isused){
        super(id,name,pwd,sexy);
        this.isused=isused;
    }

    public boolean isIsused() {
        return isused;
    }

    public void setIsused(boolean isused) {
        this.isused = isused;
    }

    public String show(){
        return "User{" +
                "id=" + super.getId() +
                ",name=" + super.getName() +
                ",pwd=" + super.getPwd() +
                ",sexy="+ super.getSexy()+
                "isused=" + isused +
                "}";
    }
}
