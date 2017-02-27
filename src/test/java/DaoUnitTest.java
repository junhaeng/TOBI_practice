import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import test.User;
import test.UserDao;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.sql.SQLException;


/**
 * Created by jhcorea on 2017. 2. 15..
 */
public class DaoUnitTest {

    public final static String contextPath = "applicationContext.xml";

    @Test
    public void addAndGet() throws SQLException {

        ApplicationContext context = new GenericXmlApplicationContext(contextPath);
        UserDao dao = context.getBean("userDao", UserDao.class);

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        User user = new User();
        user.setId("apple");
        user.setPassword("apple");
        user.setName("grape");

        dao.add(user);
        assertThat(dao.getCount(), is(1));

        User user2 = dao.get(user.getId());

        assertThat(user2.getName(), is(user.getName()));
        assertThat(user2.getPassword(), is(user.getPassword()));

    }
}

