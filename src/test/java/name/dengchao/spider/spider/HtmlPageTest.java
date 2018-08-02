package name.dengchao.spider.spider;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.regexp.RE;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

public class HtmlPageTest {
    @Test
    public void testGetByXpath() throws IOException, InterruptedException {
        WebRequest request = new WebRequest(new URL("http://www.xiaohuasheng.cn/read/userbooklist_2018"));
        WebClient webClient = new WebClient();
        HtmlPage page = webClient.getPage(request);
        System.out.println(page.getTitleText());
        System.out.println(page.getBody().asXml().length());
        Thread.sleep(100);
        System.out.println(page.getByXPath("/html/body/div[5]/div[2]/div/table/tbody/tr/td[1]/div/div[3]/div[*]/div[1]/a"));
//        System.out.println(page.getByXPath("/html/body/div[5]/div[2]/div/table/tbody/tr/td[3]/div[2]/div[*]/a"));
    }
}
