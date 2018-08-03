package name.dengchao.spider.spider;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.regexp.RE;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

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

    @Test
    public void testOpenNonexistURL() throws IOException {
        WebRequest request = new WebRequest(new URL("http://www.xiaohuasheng.cn/read/userbooklist_2021123"));
        WebClient webClient = new WebClient();
        HtmlPage page = null;
        try {
            page = webClient.getPage(request);
        } catch (IOException e) {
            System.out.println("page.getReadyState(): " + page.getReadyState());
            System.out.println("");
        } catch (FailingHttpStatusCodeException e) {
            System.out.println("page.getReadyState(): " + page.getReadyState());
            System.out.println("");
        }
    }

    @Test
    public void base64Converter() {
        String txt = "https://n1image.hjfile.cn/mh/2018/08/03/586162c799281b4ed061e58bb357ac8d.png?roundPic/radius/500";
        System.out.println(Base64.getEncoder().encodeToString(txt.getBytes(StandardCharsets.UTF_8)));

        String encodedTxt = "aHR0cHM6Ly9uMWltYWdlLmhqZmlsZS5jbi9taC8yMDE4LzA4LzAzLzU4NjE2MmM3OTkyODFiNGVkMDYxZTU4YmIzNTdhYzhkLnBuZw==";
        System.out.println(new String(Base64.getDecoder().decode(encodedTxt), StandardCharsets.UTF_8));
    }
}
