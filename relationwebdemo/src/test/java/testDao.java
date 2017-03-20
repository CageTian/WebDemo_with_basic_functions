import com.relation.user.dao.UserDao;
import com.relation.user.domain.User;
import org.junit.Test;

/**
 * Created by T.Cage on 2017/3/20.
 */
public class testDao {
    private UserDao userDao=new UserDao();
    @Test
    public void test1(){
        User user=new User();
        user.setVerifyCode("sxg3");
        user.setId(54);
        user.setUsername("tiankaiqi");
        user.setPassword("1234");
        user.setEmail("352986331@qq.com");
        //userDao.addUser(user);
        userDao.changeUser(user,"state",10);
        //userDao.changeUser(user,"username","password","email","phonenumber","state","asd","tiankaiqi","352s986331@qq.com","123213123123",1);


        User user2=new User();
        user2.setId(5);
        user2.setUsername("tiankaiqi");
        user2.setPassword("12345");
        //regist(user);
        //userDao.addUser(user);
        //User utmp=userDao.findByUsername("tiankaiqi");
        //userDao.updUser(user2);
        //userDao.delUser(user2);
    }
}
