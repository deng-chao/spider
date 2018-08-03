package name.dengchao.spider.spider;

import static org.junit.Assert.*;

import java.net.URI;

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
}
