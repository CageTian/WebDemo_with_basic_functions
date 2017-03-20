package com.relation.user.dao;
import com.relation.user.domain.User;
import com.relation.utils.JDBC.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.sql.*;
import java.util.Calendar;


/**
 * Created by T.Cage on 2016/12/19.
 */
public class UserDao{
    private QueryRunner qr = new TxQueryRunner();

    public void addUser(User user)
    {
        try {
            String sql="INSERT INTO users (username,password,email,phonenumber,state,acticode,registetime) VALUES(?,?,?,?,?,?,?)";
            Object[] params = {user.getUsername(), user.getPassword(), user.getEmail(),
                    user.getPhonenumber(), user.getState(), user.getActicode(), new Timestamp(Calendar.getInstance().getTime().getTime())};
            qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int changeUser(User user,Object ...args){
        int statue=0;
        try {
            String sql="UPDATE users set ";
            for(int i=0;i<args.length/2;i++)
                sql+=((String)args[i]+"=?,");
            sql=sql.substring(0,sql.length()-1)+" WHERE id=?";
            Object[] params=new Object[args.length/2+1];
            for(int i=0;i<args.length/2;i++)
                params[i]=args[args.length/2+i];
            params[args.length/2]=user.getId();
            qr.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statue;
    }//第一个参数为查询参数，之后参数为需要更新的键，全部列出键之后，列出全部的值
    public User findUser(Object key,Object value) throws SQLException {
        User user=new User();
        String sql="select * from users WHERE "+(String)key+"=?";
        return qr.query(sql, new BeanHandler<User>(User.class), value);
    }

    public boolean findByUsername(String username) throws SQLException {
        String sql="select * from users WHERE username=?";
        Number number = (Number)qr.query(sql, new ScalarHandler(), username);
        return number==null||number.intValue() == 0;
    }
    public boolean findByEmail(String email) throws SQLException {
        String sql="select * from users WHERE email=?";
        Number number = (Number)qr.query(sql, new ScalarHandler(), email);
        return number==null||number.intValue() == 0;
    }
}
