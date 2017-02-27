import org.junit.runner.JUnitCore;

import java.net.URL;
import java.net.URLClassLoader;
import java.sql.SQLException;

/**
 * Created by jhcorea on 2017. 2. 15..
 */
public class SampleTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader)cl).getURLs();

        for(URL url: urls){
            System.out.println(url.getFile());
        }

        Class s = Class.forName("DaoUnitTest");
        JUnitCore.main("DaoUnitTest");

    }
}

