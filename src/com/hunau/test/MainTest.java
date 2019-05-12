package com.hunau.test;/* *
 * @Description:
 * @param $params$
 * @Return: $returns$
 * @开发人员：余新伟
 * @开发单位：湖南农业大学物联网工程专业
 * @Date: 2019/5/11 17:25
 * @开发版本：综合练习V0.1
 */

import com.hunau.util.Listen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.WindowConstants;
import javax.swing.*;

public class MainTest extends JFrame{
    public void Login(){
        Container container=getContentPane();
        container.setLayout(null);

        JLabel j11=new JLabel("用 户 名");
        JLabel j12=new JLabel("密    码");
        JTextField jtf=new JTextField();
        JPasswordField jpf=new JPasswordField();
        JButton jb1=new JButton("登录");
        JButton jb2=new JButton("清空");
        JTextArea jta=new JTextArea();

        container.add(j11);
        j11.setBounds(30,30,100,30);
        container.add(j12);
        j12.setBounds(30,80,100,30);
        container.add(jtf);
        jtf.setBounds(140,30,150,30);
        container.add(jpf);
        jpf.setBounds(140,80,150,30);
        container.add(jb1);
        jb1.setBounds(60,130,80,30);
        container.add(jb2);
        jb2.setBounds(160,130,80,30);
        container.add(jta);
        jta.setBounds(30,170,300,100);

        setTitle("用户登录");
        setVisible(true);
        setBounds(200,200,380,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Listen ll=new Listen(jtf,jpf,jta);
        jb1.addActionListener(ll);
        jb2.addActionListener(ll);
    }
    public static void main(String[] args){
        MainTest tt=new MainTest();
        tt.Login();
    }
}

