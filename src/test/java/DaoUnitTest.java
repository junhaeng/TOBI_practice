import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import dao.User;
import dao.UserDao;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;


/**
 * Created by jhcorea on 2017. 2. 15..
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class DaoUnitTest {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private UserDao dao;

    private User user1;
    private User user2;
    private User user3;
    static ApplicationContext contextObject = null;

    @Before
    public void setUp() {
        this.user1 = new User("id1", "이름1", "password1");
        this.user2 = new User("id2", "이름2", "password2");
        this.user3 = new User("id3", "이름3", "password3");
    }

    @Test
    public void addAndGet() throws SQLException {

        setUp();

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        User userget1 = dao.get(user1.getId());
        assertThat(userget1.getName(), is(user1.getName()));
        assertThat(userget1.getPassword(), is(user1.getPassword()));

        User userget2 = dao.get(user2.getId());
        assertThat(userget2.getName(), is(user2.getName()));


    }

    @Test
    public void count() throws SQLException {
        setUp();

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        assertThat(dao.getCount(), is(1));

        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        dao.add(user3);
        assertThat(dao.getCount(), is(3));

    }

    @Test(expected= EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException {
        setUp();
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.get("unknown_id");
    }
}

