import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by jhcorea on 2017. 2. 28..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test_applicationContext.xml")
public class JUnitTest {
    @Autowired
    ApplicationContext context;

    static ApplicationContext contextObject = null;

    static Set<JUnitTest> testObjects = new HashSet<JUnitTest>();

    @Test
    public void test1(){
        assertThat(testObjects, not(hasItem(this)));
        testObjects.add(this);

        assertThat(contextObject == null || contextObject == this.context, is(true));
        contextObject = this.context;
    }

    @Test
    public void test2(){
        assertThat(testObjects, not(hasItem(this)));
        testObjects.add(this);

        assertTrue(contextObject == null || contextObject == this.context);
        contextObject = this.context;
    }

    @Test
    public void test3(){
        assertThat(testObjects, not(hasItem(this)));
        testObjects.add(this);

        assertThat(contextObject,
                either(is(nullValue())).or(CoreMatchers.<Object>is(this.context)));
        contextObject = this.context;
    }
}
