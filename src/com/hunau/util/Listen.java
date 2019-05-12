package com.hunau.util;/* *
 * @Description:
 * @param $params$
 * @Return: $returns$
 * @开发人员：余新伟
 * @开发单位：湖南农业大学物联网工程专业
 * @Date: 2019/5/11 17:26
 * @开发版本：综合练习V0.1
 */

import com.hunau.util.PoolConn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.hunau.entity.User;
import com.hunau.util.ConnPooled;

public class Listen implements ActionListener {
    private JTextField jtf;
    private JPasswordField jpf;
    private JTextArea jta;
    private User user;
    //private JFrame jfame;

    //定义构造函数
    public Listen() {
    }

    public Listen(JTextField jtf) {
        this.jtf = jtf;
    }

    public Listen(JTextField jtf, JPasswordField jpf) {
        this.jtf = jtf;
        this.jpf = jpf;
    }

    public Listen(JTextField jtf, JPasswordField jpf, JTextArea jta) {
        this.jtf = jtf;
        this.jpf = jpf;
        this.jta = jta;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btnsel = e.getActionCommand();//返回与此动作相关的命令字符串
        if(btnsel.equals("登录")){
            ConnPooled db=new ConnPooled();
            if((jtf.getText().toString().length() > 0) && (jpf.getText().toString().length() > 0)){
                user=db.UserLogin(jtf.getText().toString(),jpf.getText().toString());
                /*if(!user.equals("")){*/
                    jta.setText("你登录成功，你的登录信息是"+user.show());
                    jtf.setText("");
                    jpf.setText("");
               /* }
                else {
                    jta.setText("你登录失败！");
                }*/
            }
            else{
                JOptionPane.showMessageDialog(null, "登录失败！", "提示", JOptionPane.YES_OPTION);
            }
        }
        else if (btnsel.equals("清空")){
            jtf.setText("");
            jpf.setText("");
            jta.setText("清除成功！");
        }
    }
}
