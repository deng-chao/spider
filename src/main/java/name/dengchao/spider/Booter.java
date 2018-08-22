package name.dengchao.spider;

import lombok.extern.slf4j.Slf4j;
import name.dengchao.spider.domain.ToVisitLink;
import name.dengchao.spider.spider.Spider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@Slf4j
@SpringBootApplication
public class Booter implements InitializingBean {

    // -Dcom.sun.management.jmxremote.port=9999
    // -Dcom.sun.management.jmxremote.authenticate=false
    // -Dcom.sun.management.jmxremote.ssl=false
    // -Xmx1024m
    // -javaagent:C:\Users\izene\workspace\myagent\myagent.jar

    @Autowired
    Spider spider;

    public static void main(String[] args) {
        System.getProperties().put("org.apache.commons.logging.simplelog.defaultlog", "fatal");
        SpringApplication.run(Booter.class, args);
    }

    @Value("${xiaohuasheng.start}")
    private int start;

    @Value("${xiaohuasheng.end}")
    private int end;

    @Override
    public void afterPropertiesSet() throws Exception {
        Thread t = new Thread(spider);
        t.start();
        Thread producer = new Thread(() -> {
            for (int i = start; i <= end; i++) {
//                String url = "http://www.xiaohuasheng.cn/read/userbooklist_" + i;
                String url = "http://www.xiaohuasheng.cn/read/booklist_" + i + "/age_-1";
                long currSize = spider.getToVisitUrls().size();
                try {
                    if (currSize < 100) {
                        Thread.sleep(100);
                    } else if (currSize >= 100 && currSize < 500) {
                        Thread.sleep(1000);
                    } else if (currSize >= 500 && currSize < 1000) {
                        Thread.sleep(5000);
                    } else {
                        Thread.sleep(10000);
                    }

                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                }
                log.info("offer url: " + url);
                spider.getToVisitUrls().offer(new ToVisitLink(url, "generated", new Date()));
            }
        });
        producer.start();
    }
}
