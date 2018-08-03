package iter;

import name.dengchao.spider.SpiderConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JsoupTest {

    ApplicationContext ctx = new AnnotationConfigApplicationContext(SpiderConfig.class);

    @Test
    public void testBaseUrl() {
    }
}
