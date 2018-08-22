package name.dengchao.spider.spider;

import static org.junit.Assert.*;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.Test;

public class SpiderTest {

    @Test
    public void testFormatUrl() throws Exception {

        String processingUrl = "http://www.baidu.com/abc/cde";
        URI processingURI = URI.create(processingUrl);
        String toVisitLink = "http://www.baidu.com/abc";
        Spider spider = new Spider();
        String result = spider.formatUrl(toVisitLink, processingURI);
        assertEquals(toVisitLink, result);

        toVisitLink = "abc";
        result = spider.formatUrl(toVisitLink, processingURI);
        assertEquals("http://www.baidu.com/abc/abc", result);

        toVisitLink = "/abc";
        result = spider.formatUrl(toVisitLink, processingURI);
        assertEquals("http://www.baidu.com/abc", result);
    }

    @Test
    public void testFormatUrl2() throws Exception {

        String processingUrl = "http://www.baidu.com";
        URI processingURI = URI.create(processingUrl);
        String toVisitLink = "http://www.baidu.com/abc";
        Spider spider = new Spider();
        String result = spider.formatUrl(toVisitLink, processingURI);
        assertEquals(toVisitLink, result);

        toVisitLink = "abc";
        result = spider.formatUrl(toVisitLink, processingURI);
        assertEquals("http://www.baidu.com/abc", result);

        toVisitLink = "/abc";
        result = spider.formatUrl(toVisitLink, processingURI);
        assertEquals("http://www.baidu.com/abc", result);
    }

    @Test
    public void testConvertToAbsPath() throws Exception {
        String path = "http://www.chinadaily.com.cn/world/2017-01/06/../../../china/2017-01/03/../01/content_27835153.htm";
        Spider spider = new Spider();
        path = spider.convertToAbsPath(path);
        assertEquals("http://www.chinadaily.com.cn/china/2017-01/01/content_27835153.htm", path);
    }

    @Test
    public void base64() {
        String base64 = Base64.getEncoder().encodeToString("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=7867671b40a98226accc2375ebebd264/faf2b2119313b07e5923b5660fd7912396dd8cc6.jpg".getBytes(StandardCharsets.UTF_8));
        System.out.println(base64);
    }
}
