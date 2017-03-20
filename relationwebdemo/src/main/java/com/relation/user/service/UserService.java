package com.relation.user.service;

import com.relation.user.dao.UserDao;
import com.relation.user.domain.User;
import com.relation.utils.Common.CommonUtils;
import com.relation.utils.avtivmail.Mail;
import com.relation.utils.avtivmail.MailUtils;
import org.junit.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.sql.SQLException;

/**
 * Created by T.Cage on 2016/12/19.
 */
public class UserService {
    private UserDao userDao=new UserDao();
    public boolean ajaxVerifyUsername(String username) {
        try {
            return userDao.findByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean ajaxVerifyEmail(String email){
        try {
            return userDao.findByEmail(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public User login(User user){
        try {
            User usertmp=userDao.findUser("username",user.getUsername());
            if(usertmp.getUsername()==null)
                return null;
            System.out.println(usertmp);
            if(usertmp.getPassword().equals(user.getPassword()))
                return usertmp;
            else return null;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    public void regist(User user){
        user.setState(0);
        user.setActicode(UUID.randomUUID().toString().replace("-", "").toUpperCase());
        userDao.addUser(user);

        Properties prop=new Properties();
        try {
            //System.out.println(this.getClass().getClassLoader().getResourceAsStream("mail.properties"));
            prop.load(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("mail.properties"), "UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException();
        }
        String host=prop.getProperty("host");
        String name=prop.getProperty("username");
        String pass=prop.getProperty("password");
        javax.mail.Session session= MailUtils.createSession(host,name,pass);

        String from=prop.getProperty("from");
        String to=user.getEmail();
        String subject=prop.getProperty("subject");
        String content= MessageFormat.format(prop.getProperty("content"),user.getActicode(),user.getUsername());
        Mail mail=new Mail(from,to,subject,content);

        try {
            MailUtils.send(session,mail);
        } catch (MessagingException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
    public void updatePassword(int id,String newPass,String password) throws UserException {
        User user= null;
        try {
            user = userDao.findUser("id",id);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        if(!user.getPassword().equals(password))
            throw new UserException();
        userDao.changeUser(user,"password",newPass);
    }
    public void activation(String username,String code) throws UserException {
        User user = null;
        try {
            user = userDao.findUser("username",username);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        if(code==null)
            throw new UserException("无效的激活码");
        if(!user.getActicode().equals(code))
            throw new UserException("激活码错误！");
        if(user.getState()!=0)
            throw new UserException("您已经激活过了，不要二次激活！");
        userDao.changeUser(user,"state",1);
        //userDao.updateStatus(user.getState(), 1);//修改状态

    }

}
