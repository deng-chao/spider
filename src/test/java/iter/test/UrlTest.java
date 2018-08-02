package iter.test;

import java.net.URL;

import org.junit.Test;
import org.springframework.web.util.UrlPathHelper;

public class UrlTest {

    @Test
    public void testParseUrl() throws Exception {
        UrlPathHelper help = new UrlPathHelper();
        String aa = help.removeSemicolonContent("http://www.chinadaily.com.cn/world/2017-0"
                + "1/06/../../../china/2017-01/03/../01/content_27835153.htm");
        System.out.println(aa);
        URL aURL = new URL("http://www.chinadaily.com.cn/world/2017-0"
                + "1/06/../../../china/2017-01/03/../01/content_27835153.htm");
        System.out.println("protocol = " + aURL.getProtocol());
        System.out.println("authority = " + aURL.getAuthority());
        System.out.println("host = " + aURL.getHost());
        System.out.println("port = " + aURL.getPort());
        System.out.println("path = " + aURL.getPath());
        System.out.println("query = " + aURL.getQuery());
        System.out.println("filename = " + aURL.getFile());
        System.out.println("ref = " + aURL.getRef());
        
        
    }
}
